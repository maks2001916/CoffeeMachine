package org.example

suspend fun main() {
    val coffee = Coffee.Americano(Sugar.GR_0, "0")
    coffee.selectedMenu()
}