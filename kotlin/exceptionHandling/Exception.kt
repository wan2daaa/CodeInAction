package exceptionHandling

fun main() {

    /**
     * 코틀린은 기본적으로 Checked Exception을 강제하지 않음
     */

    Thread.sleep(1)

    /**
     * 예외 처리를 해야하는 경우 try catch 감싸줄 수 있음
     */
    try {
        Thread.sleep(1)
    } catch (e: Exception) {
        println("에러 발생")
    }

    try {
        throw Exception()
    } catch (e: Exception) {
        println("에러 발생!")
    }finally {
        println("finally 실행!!")
    }

    /**
     * 코틀린에서는 try catch 는 표현식이다!!
     */

    val a = try {
        "1234".toInt()
        throw Exception()
    } catch (e: Exception) {
        "01012341234".toInt()
//        println("예외발생!")
    }
    println(a)

//    throw Exception("예외 발생!!")

/*
    failFast(message = "예외발생!!!")
    println("이 메시지는 출력될까?")
*/

/*
    val b: String? = "널이 아님"
//    val b: String? = null
    val c = b ?: failFast("a is null")

    println(c.length)
*/


}

/**
 * 리턴하는게 없을 떄 Unit 이 리턴타입을 하는데
 * 이 메서드가 문제가 생기거나 발생할 수 있으면 사용
 * Elvis 연산자와 Nothing 타입을 함께 사용하면 절대 null 이 안나오는 타입으로 만들 수 있기 때문에 유용함!!
 */
fun failFast(message: String): Nothing {
    throw IllegalArgumentException(message)
}