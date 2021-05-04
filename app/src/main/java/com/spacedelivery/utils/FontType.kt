package com.spacedelivery.utils

enum class FontType {
    LIGHT(0),
    NORMAL(1),
    BOLD(2),
    MEDIUM(3);

    private var index: Int = 0

    constructor(i: Int) {
        index = i
    }

    public fun getIndex(): Int {
        return index
    }

    companion object {
        fun parse(i: Int): FontType? {
            for (item in FontType.values()) {
                if (item.getIndex() == i) {
                    return item
                }
            }

            return null
        }
    }
}