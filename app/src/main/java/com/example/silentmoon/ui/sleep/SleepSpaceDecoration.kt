package com.example.silentmoon.ui.sleep

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SleepSpaceDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = space
        } else {
            outRect.left = space
        }
        outRect.bottom = space
    }
}