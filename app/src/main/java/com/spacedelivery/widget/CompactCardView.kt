package com.spacedelivery.widget

import android.content.Context
import android.os.Build
import androidx.cardview.widget.CardView
import android.util.AttributeSet

class CompactCardView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?) : CardView(context!!, attrs) {
    init {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            cardElevation = 0f
            maxCardElevation = 0f
            preventCornerOverlap = false
        }
    }
}