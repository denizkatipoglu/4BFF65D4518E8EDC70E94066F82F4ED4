package com.spacedelivery.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.spacedelivery.R

class BottomNavigationItemView : LinearLayout {

    private var inflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var menuItem: Menu? = null

    var tabSelected: Boolean = false

    var imIcon: ImageView

    init {
        inflater.inflate(R.layout.widget_bottom_navigation_item, this, true)

        orientation = VERTICAL
    }

    constructor(context: Context?, menuItem: Menu, task: (Menu) -> Unit) : super(context) {
        this.menuItem = menuItem

        imIcon = findViewById(R.id.imIcon)

        if (menuItem == Menu.HOME) {
            setSelection(true)
        }

        setState()

        setOnClickListener { task(menuItem) }
    }

    private fun setSelection(isSelected: Boolean) {
        tabSelected = isSelected
    }

    private fun setState() {
        when (tabSelected) {
            true -> {
                imIcon.setImageResource(menuItem!!.drawableSelectedResId)
            }
            false -> {
                imIcon.setImageResource(menuItem!!.drawableUnselectedResId)
            }
        }
    }

    fun setSelection(item: Menu) {
        tabSelected = false

        if (menuItem == item) tabSelected = true

        setState()
    }
}