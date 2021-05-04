package com.spacedelivery.utils

import android.content.Context
import android.graphics.Typeface

class TypeFaces {

    companion object {
        fun getFont(context: Context, fontType: FontType): Typeface {
            return Typeface.createFromAsset(context.assets, getFontPath(fontType))
        }

        private fun getFontPath(fontType: FontType): String {
            return when (fontType) {
                FontType.LIGHT -> "fonts/Poppins-Light.ttf"
                FontType.NORMAL -> "fonts/Poppins-Regular.ttf"
                FontType.BOLD -> "fonts/Poppins-SemiBold.ttf"
                FontType.MEDIUM -> "fonts/Poppins-Medium.ttf"
            }
        }
    }
}