package com.example.silentmoon.timepicker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.silentmoon.R
import top.leefeng.datepicker.PickerTextView
import top.leefeng.datepicker.PickerView
import java.time.LocalTime
import java.time.format.DateTimeFormatter


// за основу взят этот проект
// https://github.com/limxing/DatePickerView

class TimePickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private var textSideColor: Int
    private var textColor: Int
    private var textSize: Float
    private var padTop: Float
    private var lineStrokeWidth: Float
    private var datePaddingEnd: Int = 0
    private var datePaddingStart: Int = 0
    private var backColor: Int
    private var lineColor: Int
    private var oneRecyclerW: Int = 2
    private var cellHeight: Int = 0
    private var dateShowSize: Int = 7
    private var sizeHeight: Int = 0
    private var sizeWidth: Int = 0
    private var isScrolling = false
    private val result = IntArray(3)
    var listener: ((IntArray) -> Unit)? = null

    private var drawListener: PickerView.DrawListener? = null


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        private var lastTag: Any = 0
        private var lastValue = ""
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == SCROLL_STATE_IDLE) {
                val value = getCurrentText(recyclerView)
                if (recyclerView.tag == lastTag && lastValue == value) return
                lastTag = recyclerView.tag
                lastValue = value
                when (recyclerView.tag) {
                    0 -> {
                        setHours(
                            recyclerView.adapter as TimeAdapter
                        )
                    }

                    1 -> {
                        setMinutes(
                            recyclerView.adapter as TimeAdapter
                        )
                    }

                    2 -> {
                        setAmPm(
                            recyclerView.adapter as TimeAdapter
                        )
                    }
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            result[recyclerView.tag as Int] = getCurrentText(recyclerView).toInt()
            listener?.invoke(result)
        }
    }

    private fun getCurrentText(recyclerView: RecyclerView): String {
        var text = (recyclerView.findViewHolderForAdapterPosition(
            (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() + dateShowSize / 2
        )?.itemView as? TextView)?.text?.toString() ?: "0"
        if (recyclerView.tag == 2) {
            text = if (text == "AM") "1" else "0"
        }
        return when (text.length) {
            0 -> "0"
            1 -> "0$text"
            else -> text
        }
    }


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    init {
        setWillNotDraw(false)
        val it = context.obtainStyledAttributes(attrs, R.styleable.TimePickerView)
        textColor = it.getColor(R.styleable.TimePickerView_tpvTimeTextColor, Color.BLACK)
        textSideColor = it.getColor(R.styleable.TimePickerView_tpvTimeTextSideColor, textColor)
        textSize = it.getDimension(
            R.styleable.TimePickerView_tpvTimeTextSize, 20 * resources.displayMetrics.density
        )
        datePaddingEnd = it.getDimension(
            R.styleable.TimePickerView_tpvTimePaddingEnd, resources.displayMetrics.density
        ).toInt()
        datePaddingStart = it.getDimension(
            R.styleable.TimePickerView_tpvTimePaddingStart, resources.displayMetrics.density
        ).toInt()
        lineStrokeWidth = it.getDimension(
            R.styleable.TimePickerView_tpvLineWidth, resources.displayMetrics.density
        )
        lineColor = it.getColor(R.styleable.TimePickerView_tpvLineColor, Color.TRANSPARENT)
        backColor = it.getColor(R.styleable.TimePickerView_tpvBackgroundColor, Color.TRANSPARENT)
        val isEnableAlpha = it.getBoolean(R.styleable.TimePickerView_tpvTimeEnableAlpha, true)
        padTop = it.getDimension(R.styleable.TimePickerView_tpvPaddingTop, 0f)
        it.recycle()
        repeat(3) {
            addView(PickerView(context).apply {
                tag = it
                addOnScrollListener(scrollListener)
                enableAlpha = isEnableAlpha
            })
        }
        post {
            children.forEach {
                if (it is RecyclerView) {
                    it.adapter = TimeAdapter(
                        cellHeight,
                        textSize,
                        textColor,
                        textSideColor,
                        it.tag != 2
                    )
                    scrollToPosition(it)
                }
            }

        }
        listener?.invoke(result)
    }


    private fun scrollToPosition(recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter as TimeAdapter

        val index = when (recyclerView.tag) {
            0 -> {
                adapter.setData(1, 12)
                result[0] + (Integer.MAX_VALUE / 2) + 4
            }

            1 -> {
                adapter.setData(0, 59)
                result[1] + (Integer.MAX_VALUE / 2) + 24
            }

            else -> {
                adapter.setData(0, 1)
                result[2]
            }
        }

        (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(index, 0)
        recyclerView.postDelayed({
            result[recyclerView.tag as Int] = getCurrentText(recyclerView).toInt()
        }, 500)
    }


    private fun setHours(adapter: TimeAdapter) {
        adapter.setData(1, 12)
    }

    private fun setMinutes(adapter: TimeAdapter) {
        adapter.setData(0, 59)
    }


    private fun setAmPm(adapter: TimeAdapter) {
        adapter.setData(0, 1)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var left = datePaddingStart
        children.forEachIndexed { index, view ->
            val isRecycler = view is RecyclerView
            val right = left + if (index == 0) {
                5 * oneRecyclerW
            } else if (isRecycler) {
                oneRecyclerW * 4
            } else {
                view.measuredWidth
            }
            view.layout(
                left,
                if (isRecycler) 0 else (measuredHeight - view.measuredHeight) / 2,
                right,
                if (isRecycler) measuredHeight else (measuredHeight + view.measuredHeight) / 2
            )
            left = right
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        val cellHeight = sizeHeight / dateShowSize
        if (cellHeight != this.cellHeight) {
            children.forEach {
                if (it is RecyclerView) {
                    (it.adapter as? TimeAdapter)?.cellHeight = cellHeight
                }
            }
        }
        this.cellHeight = cellHeight
        sizeHeight = cellHeight * dateShowSize
        setMeasuredDimension(sizeWidth, sizeHeight)
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        oneRecyclerW =
            (sizeWidth - (get(1).measuredWidth) * 3 - datePaddingStart - datePaddingEnd) / 13
    }


    override fun onDraw(canvas: Canvas) {
        canvas.translate(0f, padTop)
        drawListener?.drawBelow(canvas, measuredWidth, measuredHeight, cellHeight)
        rectF.set(
            0f, (sizeHeight - cellHeight) / 2f, sizeWidth / 1f, (sizeHeight + cellHeight) / 2f
        )
        paint.reset()
        paint.strokeWidth = lineStrokeWidth
        paint.color = backColor
        paint.style = Paint.Style.FILL
        canvas.drawRect(rectF, paint)

        paint.color = lineColor
        paint.style = Paint.Style.STROKE
        canvas.drawLine(rectF.left, rectF.top, rectF.right, rectF.top, paint)
        canvas.drawLine(rectF.left, rectF.bottom, rectF.right, rectF.bottom, paint)
        super.onDraw(canvas)
        drawListener?.drawOver(canvas, measuredWidth, measuredHeight, cellHeight)
    }

    private class TimeAdapter(
        var cellHeight: Int,
        val timeTextSize: Float,
        val textColor: Int,
        val textSelectColor: Int,
        val isEndlessScroll: Boolean = true
    ) : RecyclerView.Adapter<ViewHolder>() {

        private var fromNum = 0
        private var endNum = -1
        private val MAX_VALUE = Int.MAX_VALUE

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return object : ViewHolder(PickerTextView(parent.context).apply {
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, cellHeight)
                gravity = Gravity.CENTER
                setTextSize(TypedValue.COMPLEX_UNIT_PX, timeTextSize)
                setTextColor(textSelectColor)
                pTextSelectColor = textColor
                pTextColor = textSelectColor
                typeface = ResourcesCompat.getFont(context, R.font.helvetica_neue_medium)
            }) {

            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (!isEndlessScroll) {
                if (position < 3 || position > itemCount - 4) {
                    holder.itemView.visibility = GONE
                    return
                }
                holder.itemView.visibility = VISIBLE
            }
            val textView = holder.itemView as TextView
            val value = position % (endNum - fromNum + 1) + fromNum
            textView.text =
                if (isEndlessScroll) {
                    value.toString()
                } else {
                    if (value == 1) "AM" else "PM"
                }
        }

        override fun getItemCount(): Int {
            if (!isEndlessScroll) {
                return endNum - fromNum + 7
            }
            return MAX_VALUE
        }

        fun setData(from: Int, end: Int): Boolean {
            if (from == fromNum && end == endNum) {
                return false
            }
            fromNum = from
            endNum = end
            notifyDataSetChanged()
            return true
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (isScrolling) return false
        when (ev?.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> return false
        }
        return super.dispatchTouchEvent(ev)
    }

    fun getSelectedTime(): LocalTime {
        val hours = "${result[0]}".padStart(2, '0')
        val minutes = "${result[1]}".padStart(2, '0')
        val amPm = if (result[2] == 1) "AM" else "PM"
        return LocalTime.parse("$hours:$minutes $amPm", DateTimeFormatter.ofPattern("hh:mm a"))
    }
}