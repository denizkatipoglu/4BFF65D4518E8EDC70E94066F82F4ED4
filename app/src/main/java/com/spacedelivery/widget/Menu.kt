package com.spacedelivery.widget

import com.spacedelivery.R
import java.io.Serializable

enum class Menu(val drawableSelectedResId: Int, val drawableUnselectedResId: Int) : Serializable {
    HOME(R.drawable.ic_rocket_full, R.drawable.ic_rocket_empty),
    FAVORITE(R.drawable.ic_full_fav, R.drawable.ic_empty_fav)

}