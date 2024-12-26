package at.kocmana.helper

fun <T> getPairCombinations(input: List<T>): List<Pair<T, T>> {
    val combinations = mutableListOf<Pair<T, T>>()
    input.forEach { element ->
        val otherElements = input.minus(element)
        otherElements.forEach { combinations.add(element to it) }
    }
    return combinations.toList()
}

