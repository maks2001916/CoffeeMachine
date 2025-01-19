package org.example

enum class Sugar(val grams: Int) {
    GR_0(0),
    GR_5(1),
    GR_10(2),
    GR_25(3),
    GR_30(4);

    companion object {
        fun fromValue(value: Int): Sugar? {
            return entries.find { it.grams == value }
        }
    }
}