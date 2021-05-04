package com.spacedelivery.base

import com.spacedelivery.R


enum class AnimationType {
    ENTER_FROM_LEFT,
    ENTER_FROM_RIGHT,
    ENTER_FROM_BOTTOM,
    ENTER_WITH_ALPHA,
    ENTER_FROM_RIGHT_STACK,
    ENTER_FROM_RIGHT_NO_ENTRANCE,
    NO_ANIM,
    ENTER_FROM_BOTTOM_SLOWER;

    companion object {
        fun getAnimation(type: AnimationType): IntArray {
            when (type) {
                ENTER_FROM_LEFT -> return intArrayOf(R.anim.anim_horizontal_fragment_in_from_pop, R.anim.anim_horizontal_fragment_out_from_pop, R.anim.anim_horizontal_fragment_in, R.anim.anim_horizontal_fragment_out)
                ENTER_FROM_RIGHT -> return intArrayOf(R.anim.anim_horizontal_fragment_in, R.anim.anim_horizontal_fragment_out, R.anim.anim_horizontal_fragment_in_from_pop, R.anim.anim_horizontal_fragment_out_from_pop)
                ENTER_FROM_BOTTOM -> return intArrayOf(R.anim.anim_vertical_fragment_in, R.anim.anim_vertical_fragment_out, R.anim.anim_vertical_fragment_in_from_pop, R.anim.anim_vertical_fragment_out_from_pop)
                ENTER_WITH_ALPHA -> return intArrayOf(R.anim.anim_alphain, R.anim.anim_alphaout, R.anim.anim_alphain, R.anim.anim_alphaout)
                ENTER_FROM_RIGHT_STACK -> return intArrayOf(R.anim.anim_open_next, R.anim.anim_close_main, R.anim.anim_open_main, R.anim.anim_close_next)
                ENTER_FROM_RIGHT_NO_ENTRANCE -> return intArrayOf(0, R.anim.anim_horizontal_fragment_out, R.anim.anim_horizontal_fragment_in_from_pop, R.anim.anim_horizontal_fragment_out_from_pop)
                ENTER_FROM_BOTTOM_SLOWER -> return intArrayOf(R.anim.anim_vertical_fragment_in_long, R.anim.anim_vertical_fragment_out_long, R.anim.anim_vertical_fragment_in_from_pop_long, R.anim.anim_vertical_fragment_out_from_pop_long)
            }

            return intArrayOf(R.anim.anim_alphain, R.anim.anim_alphaout, R.anim.anim_alphain, R.anim.anim_alphaout)
        }

        fun getActivityAnimation(type: AnimationType): IntArray {
            when (type) {
                ENTER_FROM_LEFT -> return intArrayOf(R.anim.activity_open, R.anim.activity_open_translate_from_left, R.anim.activity_close, R.anim.activity_close_translate_to_left)
                ENTER_FROM_RIGHT -> return intArrayOf(R.anim.activity_open, R.anim.activity_open_translate_from_right, R.anim.activity_close, R.anim.activity_close_translate_to_right)
                ENTER_FROM_BOTTOM -> return intArrayOf(R.anim.activity_open, R.anim.activity_open_translate_from_bottom, R.anim.activity_close, R.anim.activity_close_translate_to_bottom)
                ENTER_WITH_ALPHA -> return intArrayOf(R.anim.activity_open, R.anim.activity_open_alpha, R.anim.activity_close, R.anim.activity_close_alpha)
            }

            return intArrayOf(R.anim.activity_open, R.anim.activity_open_alpha, R.anim.activity_close, R.anim.activity_close_alpha)
        }
    }
}