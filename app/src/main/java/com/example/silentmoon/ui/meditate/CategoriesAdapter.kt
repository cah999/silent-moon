package com.example.silentmoon.ui.meditate

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.MeditateCategoryBinding

class CategoriesAdapter(private val theme: String) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private val categories = listOf(
        Category(R.drawable.ic_all, R.string.all),
        Category(R.drawable.ic_my, R.string.my),
        Category(R.drawable.ic_anxious, R.string.anxious),
        Category(R.drawable.ic_sleep, R.string.sleep),
        Category(R.drawable.ic_kids, R.string.kids)
    )

    inner class ViewHolder(binding: MeditateCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var background: ToggleButton = binding.categoryToggle
        var icon: ImageView = binding.categoryIcon
        var filterName: TextView = binding.categoryName
    }

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val binding =
            MeditateCategoryBinding.inflate(LayoutInflater.from(group.context), group, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.icon.setImageResource(item.icon)
        holder.filterName.setText(item.filterName)

        val isLightTheme = theme == "light"
        val selectedColor =
            if (isLightTheme) R.color.dark_gray else R.color.bottom_nav_selected_color
        val unselectedColor =
            if (isLightTheme) R.color.bottom_nav_selected_color else R.color.dark_gray

        holder.background.apply {
            setBackgroundResource(if (isLightTheme) R.drawable.filter_toggle_bg else R.drawable.filter_night_toggle_bg)
            setTextColor(
                ContextCompat.getColor(
                    context,
                    if (isChecked) selectedColor else unselectedColor
                )
            )
            isChecked = position == 0
            if (isChecked) {
                holder.filterName.setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (isLightTheme) R.color.dark_gray else R.color.white
                    )
                )
            }
            setOnCheckedChangeListener { _, isChecked ->
                holder.filterName.setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (isChecked) selectedColor else unselectedColor
                    )
                )
            }
        }
    }

    override fun getItemCount() = categories.size
}