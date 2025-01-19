package org.example

import kotlinx.coroutines.delay

sealed class Coffee(var sugar: Sugar, var volume: String) {
    class Americano(sugar: Sugar, volume: String): Coffee(sugar, volume) {

    }
    class Cappuccino(sugar: Sugar, volume: String, var milk: Volume): Coffee(sugar, volume) {
        fun volumeMilk(volume: Volume) {

        }
    }
    class Latte(sugar: Sugar, volume: String): Coffee(sugar, volume)



}

suspend fun Coffee.selectMenu(menu: Coffee) {
    for (i in 0 .. 100 step 10) {
        print("$i% ")
        delay(200L)
    }
    println()
}

suspend fun Coffee.selectedMenu() {
    var americano: Coffee.Americano
    var cappuccino: Coffee.Cappuccino
    var latte: Coffee.Latte
    var coffee = 0
    var sugar = 0
    var volume = ""
    var milk = 0
    var count = 0
    while (true) {
        println("Выберите кофе:")
        println("1. Americano\n2. Cappuccino\n3. Latte")
        var coffeeStatus = true
        while (coffeeStatus) {
            var checkRenovate = readln()
            if (!checkRenovate.isEmpty()) {
                coffee = checkRenovate.toInt()
                coffeeStatus = false
            } else {
                println("Выберите кофе")
            }
        }

        println("Количество сахара")
        enumValues<Sugar>().forEach {
            println("$count. ${it.name}")
            count++
        }
        count = 0
        var sugarStatus = true
        while (sugarStatus) {
            var checkSugar = readln()
            if (!checkSugar.isEmpty() &&
                checkSugar.all { it.isDigit()} &&
                checkSugar.toIntOrNull()!! < 5) {
                sugar = checkSugar.toInt()
                sugarStatus = false
            } else {
                println("Выберите количество сахара")
            }
        }

        println("Объём кофе:")
        enumValues<Volume>().forEach {
            println("$count. ${it.name}")
            count++
        }
        count = 0
        var volumeStatus = true
        while (volumeStatus) {
            var checkVolume = readln()
            if (!checkVolume.isEmpty() &&
                checkVolume.toIntOrNull()!! < 3) {
                volume = checkVolume
                volumeStatus = false
            } else {
                println("Выберите объём:")
            }
        }

        if (coffee == 2) {
            println("Объём молока:")
            enumValues<Volume>().forEach {
                println("$count. ${it.name}")
                count++
            }
            count = 0
            var milkStatus = true
            while (milkStatus) {
                var checkMilk = readln()
                if (!checkMilk.isEmpty() &&
                    checkMilk.all { it.isDigit()} &&
                    checkMilk.toIntOrNull()!! < 3) {
                    milk = checkMilk.toInt()
                    milkStatus = false
                } else {
                    println("Выберите действие")
                }
            }
        }

        when (coffee) {
            1 -> {
                americano = Sugar.fromValue(sugar)
                    ?.let { Volume.fromValue(volume)
                    ?.let { it1 -> Coffee.Americano(it, it1.toString()) } }!!
                americano.selectMenu(americano)
                display(americano)
            }
            2 -> {
                cappuccino = Sugar.fromValue(sugar)
                    ?.let {
                        Volume.fromValue(volume)
                            ?.let { it1 ->
                                Volume.fromValue(milk.toString())
                                    ?.let { it2 -> Coffee.Cappuccino(it, it1.toString(), it2) }
                            }
                    }!!
                display(cappuccino)
            }
            3 -> {
                latte = Volume.fromValue(volume)
                    ?.let {
                        Sugar.fromValue(sugar)
                            ?.let { it1 -> Coffee.Latte(it1, it.toString()) }
                    }!!
                display(latte)
            }
        }

    }


}

fun display(coffee: Coffee) {
    when (coffee) {
        is Coffee.Latte -> println("Ваш кофе готов: Latte, сахар: ${coffee.sugar}, объём: ${coffee.volume}")
        is Coffee.Cappuccino -> println("Ваш кофе готов: Cappuccino, сахар: ${coffee.sugar}, объём: ${coffee.volume}, молоко: ${coffee.milk}")
        is Coffee.Americano -> println("Ваш кофе готов: Americano, сахар: ${coffee.sugar}, объём: ${coffee.volume}")
    }
}