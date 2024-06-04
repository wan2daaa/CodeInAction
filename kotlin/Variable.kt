/**
 * 탑레벨에 변수 선언 가능
 */
var x = 5

fun main() {

    x+= 1
    println(x)

    /**
     * 변수 선언 방법

     */
    val a: Int = 1

    /**
     * 타입 자동 추론 기능 있음
     */
    val b = 1

    /**
     * 지연 할당 기능이 있지만, 타입을 꼭 선언 해줘야함
     */
    val c: Int
    c = 3

//    val d
//    d =123

    /**
     * val : 상수 - 재할당 X
     * var : 변수 - 재할당 O
     */
    var e : String = "Hello"
    e = "World"

    /**
     * 한번 타입이 추론되면 다시 변경 안됨
     */
    var f = 123
//    f = "hi"

}
