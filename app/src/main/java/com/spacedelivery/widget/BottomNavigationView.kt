package com.spacedelivery.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class BottomNavigationView @JvmOverloads constructor(
        context: Context?,
        attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    private lateinit var listener: (Menu) -> Unit

    init {
        orientation = HORIZONTAL

        for (menu in Menu.values()) {
            var view = BottomNavigationItemView(context, menu) { selectedMenu ->
                listener(selectedMenu)
            }

            addView(view, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
        }
    }

    private fun notifyViews(selectedMenu: Menu) {
        for (index in 0..(childCount - 1)) {
            (getChildAt(index) as BottomNavigationItemView).setSelection(selectedMenu)
        }
    }

    fun setOnItemSelectedListener(task: (Menu) -> Unit) {
        listener = task
    }

    fun setSelection(menu: Menu) {
        notifyViews(menu)
    }
}