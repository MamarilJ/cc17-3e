fun main() {
    val age = 23
    val layers = 5
    printBirthdayGreetings(age)
    CakeandCandle(age)
    CakeTop(age)
    BottomCake(age, layers)
}

fun printBirthdayGreetings(age: Int) {
    println("Happy Birthday!")
    println("You are $age years old.")
    println("Enjoy your special day!\n")
}

fun CakeandCandle(age: Int) {
    repeat(age) {
        print("'")
    }
    println()
    repeat(age) {
        print("|")
    }
    println()
}

fun CakeTop(age: Int) {
    repeat(age) {
        print("=")
    }
    println()
}

fun BottomCake(age: Int, layers: Int) {
    repeat(layers) {
        repeat(age) {
            print("@")
        }
        println()
    }
}
