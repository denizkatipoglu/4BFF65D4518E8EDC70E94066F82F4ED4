package com.spacedelivery.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.spacedelivery.R

class DGProgress constructor(context: Context) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window!!.setDimAmount(0.8f)

        setCanceledOnTouchOutside(false)
        setCancelable(true)

        setContentView(R.layout.dg_lock_screen)
    }
}