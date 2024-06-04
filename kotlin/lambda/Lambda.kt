package lambda

/**
 * 함수형 프로그래밍(FP)의 정의
 * 수학의 함수적 개념을 참고해 만든 패러다임
 * 깔끔하고 유지보수가 용이한 SW를 만들기 위해 사용
 * 부수효과 X, 똑같은 input 이 들어오면, 항상 동일한 output 을 내놓는 순수함수의 개념을 기반으로
 * 람다, 고차함수, 쿼리, 메모이제이션, 모나드? 등을 포함함
 *
 * 어떻게 함수형 패러다임을 지원하는지 학습
 *
 * 일급 객체란 함수를 인자에 넘기거나 변수에 대입하거나 함수를 반환하는 개념들을 통틀어 1급객체라고함
 *
 * fun 으로 선언하면 1급객체의 특성을 가지지 못함
 *
 * 고차함수란 함수를 인자로 받거나 결과로 돌려주는 함수를 의미
 * 코틀린 공식 라이브러리 forEach, map 도 ()에 메소드를 담아주면 작동된다!
 * 마찬가지로 고차함수로 구현된거
 *
 *
 *
 *
 */

fun main() {
    val list = mutableListOf(printHello)

    list[0] // 함수를 이렇게 한다고 실행되지는 않고,
    list[0]()

    val func: () -> Unit = list[0]
    func()

    call(printHello)

//    val list2 = mutableListOf(printNo) //compile error
//    call(printNo) //compile error

    val result = plus(1, 3)
    println("result = $result")

    //고차함수
    val list3 = listOf("a", "b", "c")
    val printStr: (String) -> Unit = { println(it) }

    forEachStr(list3, printStr)

    val upperCase: (String) -> String = { it.uppercase() }
    println(list3.map(upperCase))

    //익명 함수
//    outerFunc() // 아무일 안 일어남
    val nonameFunc = outerFunc()
    nonameFunc()
    outerFunc()()
    outerFunc2()()

    //람다 레퍼런스
    val callReference = ::printHello
    callReference()()

    val numberList = listOf("1", "2", "3")
    numberList.map { it.toInt() }.forEach { println(it) }

    numberList.map(String::toInt).forEach{ println(it) }


}

//함수를 값이 될 수 있고, 타입이 될 수 있다. 함수 == 일급 객체
val func: () -> String = { "" }


val printHello: () -> Unit = { println("Hello") }

fun printHello2() {}

//함수를 받아 실행하는 함수
fun call(block: () -> Unit) {
    block()
}

fun printNo() = println("No!")

val printMessage: (String) -> Unit = { message: String -> println(message) }

// 프로퍼티의 타입이 명시 되었으므로, 타입을 생략해도 됨 , it도 사용가능
val printMessageShort: (String) -> Unit = { message -> println(message) }
val printMessageShort2: (String) -> Unit = { println(it) }

val plus: (Int, Int) -> Int = { a, b -> a + b }


//고차함수
fun forEachStr(collection: Collection<String>, action: (String) -> Unit) {
    for (item in collection) {
        action(item)
    }
}

//익명 함수 return 부분
fun outerFunc(): () -> Unit {

    return fun() {
        println("이것은 익명함수")
    }
}

// 함수형 프로그래밍은 위의 익명함수 케이스를 조금더 추상화 시킬수 있음
fun outerFunc2(): () -> Unit = { println("이것은 익명함수") }

//람다 표현식의 전체식
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

//최대한 생략한 람다식
val sum2 = { x: Int, y: Int -> x + y }
