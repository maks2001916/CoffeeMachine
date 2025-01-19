package org.example

enum class Volume(val vol: Int) {
    L_0_2(0),
    L_0_35(1),
    L_0_5(2);

    companion object {
        fun fromValue(value: String): Volume? {
            return entries.find { it.vol == value.toInt() }
        }
    }
}