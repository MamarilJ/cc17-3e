fun main() {
    val age = 23
    val layers = 5
  
    CakeandCandle(age)
    CakeTop(age)
    BottomCake(age, layers)
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
