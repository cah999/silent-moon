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
        Category(R.drawable.catrgory_all_icon, R.string.all),
        Category(R.drawable.catrgory_my_icon, R.string.my),
        Category(R.drawable.catrgory_anxious_icon, R.string.anxious),
        Category(R.drawable.catrgory_sleep_icon, R.string.sleep),
        Category(R.drawable.category_kids_icon, R.string.kids)
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
            if (isLightTheme) R.color.dark_gray else R.color.sleep_text_color
        val unselectedColor =
            if (isLightTheme) R.color.bottom_nav_selected_color else R.color.sleep_grey

        holder.background.apply {
            setBackgroundResource(if (isLightTheme) R.drawable.category_toggle_background else R.drawable.category_sleep_toggle_background)
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
                        if (isLightTheme) R.color.dark_gray else R.color.sleep_text_color
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