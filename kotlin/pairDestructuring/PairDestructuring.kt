package pairDestructuring

/**
 * 함수형 프로그래밍 개념에 접근 한다면
 * 투플 적용가능 (1,2)
 *
 * 코틀린에서는 두개의 인자를 받아서 처리하는 투플 형태를 기본 제공함
 * 투플을 직접 작성할 필요없이 pair 라는 클래스를 제공해줌
 * Pair 은 기본적으로 불변!
 *
 * Pair 뿐만아니라 Triple 도 존재!
 *
 * 이를 구조분해 할당으로 사용하면 편함
 * list 에서도 구조분해 할당 사용 가능!
 * list 도 componentN 함수를 가지고 있다는 뜻!
 * list는 component5 까지 존재
 *
 * componentN 함수가 있는 것들은 모두 구조 분해 할당 가능
 *
 * for loop 에서도 유용하게 사용 가능
 *
 */

fun plus(a: Int, b: Int) = a + b

//f((1,3)) = 1 + 3 = 4
//f(1,3) = 1 + 3 = 4

data class Tuple(val a: Int, val b: Int)

fun plusWithCustomTuple(tuple: Tuple) = tuple.a + tuple.b

fun plusWithPair(pair: Pair<Int, Int>) = pair.first + pair.second

fun main() {
    println(plus(1, 3))

    val plus = plusWithCustomTuple(Tuple(1, 3))
    println(plus)

    val plusWihPair = plusWithPair(Pair(1, 3))
    println("plusWihPair = $plusWihPair")

    val pair = Pair("A", 3)
//    pair.first = "B"  // 컴파일 오류
    val newPair = pair.copy(first = "B")

    println("newPair = $newPair")

    val second = newPair.component2()
    println("second = $second")

    // immutable 형식의 list로 반환됨
    val list = newPair.toList()
    println("list = $list")

    val triple = Triple(1, "C", false)
    println("triple = $triple")

    val newTriple = triple.copy(first = 100, third = true)
    println("newTriple = $newTriple")

    val component3 = newTriple.component3()
    println("component3 = $component3")

    // 구조 분해 할당
    // 내부적으로는 componentN 을 이용해서 할당해줌
    val (a: Int, b: String, c: Boolean) = newTriple

    println("$a, $b, $c")

    val tripleList = newTriple.toList()
    val (a1, a2, a3) = tripleList
    println("$a1, $a2, $a3")

//    tripleList.component1()
//    tripleList.component2()
//    tripleList.component3()
//    tripleList.component4()
//    tripleList.component5()

    val map = mutableMapOf("와닝" to "개발자", "갱주" to "개발자", Pair("페어", "로도 가능"))
    for ((key, value) in map) {
        println("${key}의 직업은 $value")
    }

}