package com.example.silentmoon.ui.course

import android.content.Context
import android.graphics.Canvas
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R

class CustomDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider = AppCompatResources.getDrawable(context, R.drawable.divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)

            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }
}