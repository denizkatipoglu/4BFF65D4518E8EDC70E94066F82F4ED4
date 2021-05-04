package com.spacedelivery.main

import javax.inject.Inject

class TestA @Inject constructor() {
    fun getText() : String {
        return "HELLO"
    }
}