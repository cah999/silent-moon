package com.example.silentmoon.ui.utils

import android.view.View

class CategoriesItemDecoration(private val margin: Int) :
    androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: android.graphics.Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.left = margin
        }
        outRect.right = margin
    }
}