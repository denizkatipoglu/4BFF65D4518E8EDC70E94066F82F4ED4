package com.spacedelivery.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import com.spacedelivery.R

class TextView @JvmOverloads constructor(context: Context?, attrs: AttributeSet?) : AppCompatTextView(
    context!!, attrs) {
    init {
        if (attrs != null) {
            val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BaseTextStyle)
            try {
                val i = typedArray.getInt(R.styleable.BaseTextStyle_typeface, FontType.NORMAL.getIndex())

                typeface = TypeFaces.getFont(context!!, FontType.parse(i)!!)
            } finally {

                typedArray.recycle()
            }
        }
    }

    fun setTypeFaces(fontType: FontType) {
        typeface = TypeFaces.getFont(context, fontType)
    }
}