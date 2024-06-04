package collection

import java.util.LinkedList

fun main() {

    //immutable 읽기전용
    val currencyList = listOf("달러", "유로", "원")
    println("currencyList = $currencyList")

    val numberSet = setOf(1, 2, 3, 4)
    println("numberSet = $numberSet")

    // to 는 코틀린에서 key value 세팅해주고 또 다른 역할도 한다고함
    val numberMap = mapOf("one" to 1, "two" to 2)
    println("numberMap = $numberMap")

    //mutable
    val mutableCurrencyList = mutableListOf<String>()
    mutableCurrencyList.add("달러")
    mutableCurrencyList.add("유로")
    mutableCurrencyList.add("원")
    println("mutableCurrencyList = $mutableCurrencyList")

    val otherMutableCurrencyList = mutableListOf<String>().apply {
        add("달러")
        add("유로")
        add("원")
    }
    println("otherMutableCurrencyList = $otherMutableCurrencyList")

    val mutableSet = mutableSetOf<Int>().apply {
        add(1)
        add(2)
        add(3)
        add(4)
    }
    println("mutableSet = $mutableSet")

    val mutableMap = mutableMapOf<String, Int>()
        mutableMap["one"] = 1
        mutableMap["two"] = 2
        mutableMap["three"] = 3
    println("mutableMap = $mutableMap")


    /**
     * 컬렉션 빌더를 이용해서 조금 더 쉽게 컬렉션 사용 가능
     * immutable 한 Collection 에서도 mutable 처럼 초기 값 설정 가능!
     */

    val numberList = buildList{
        add(1)
        add(2)
        add(3)
    }
    println("numberList = $numberList")

    //LinkedList
    val linkedList = LinkedList<Int>().apply {
        add(2)
        add(2)
        add(2)
        addFirst(3)
        addLast(1)
    }
    println("linkedList = $linkedList")

    //ArrayList
    val arrayList = ArrayList<Int>().apply {
        add(1)
        add(2)
        add(3)
    }


    /**
     * 자바와 마찬가지로 iterator 사용가능!
     */

    val iterator = currencyList.iterator()
    while (iterator.hasNext()) {
        println("iterator.next() = ${iterator.next()}")
    }

    println("================")

    /**
     * 자바의 for each 처럼 코틀린도 for loop 를 사용해서 대체 가능
     */

    for (currency in currencyList) {
        println("currency = $currency")
    }

    println("================")

    /**
     * 자바 8 stream 처럼 사용 가능
     *
     * .forEach { it 으로 각각 반복하는듯}
     */

    currencyList.forEach {
        println("it = $it")
    }

    //for loop -> map

    // without map
    val lowerList = listOf("a,b,c")
    val upperList = mutableListOf<String>()

    for (lowerCase in lowerList) {
        upperList.add(lowerCase.uppercase())
    }

    println("upperList = $upperList")

    //with map
    val otherLowerList = listOf("a,b,c")
    val otherUpperList = otherLowerList.map { it.uppercase() }

    println("otherUpperList = $otherUpperList")

    //for loop -> filter
    val filteredList = mutableListOf<String>()

    for (upperCase in otherLowerList) {
        if (upperCase == "A" || upperCase == "C") {
            filteredList.add(upperCase)
        }
    }

    println("filteredList = $filteredList")

    val otherFilteredList = upperList.filter { it == "A" || it == "C" }
    println("otherFilteredList = $otherFilteredList")

    otherFilteredList.findLast {
        it == "C"
    }

    otherFilteredList.find {
        it == "A"
    }

}