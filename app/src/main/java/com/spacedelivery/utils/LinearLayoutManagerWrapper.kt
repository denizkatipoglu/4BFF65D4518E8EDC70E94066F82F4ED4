package com.spacedelivery.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.AttributeSet

class LinearLayoutManagerWrapper : LinearLayoutManager {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, orientation: Int, reverseLayout: Boolean) : super(ctx, orientation, reverseLayout)

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(ctx, attrs, defStyleAttr, defStyleRes)

}