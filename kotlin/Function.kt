/**
 * 기본적인 함수 선언 스타일
 *
 * fun 함수명(함수인자...) : 반환타입 {}
 */

fun sum(a: Int, b: Int): Int {
    return a + b;
}

/**
 * 표현식 스타일, 반환타입도 생략가능
 * 기본적인 함수 스타일에서는 반환타입을 제거하면 컴파일 오류
 */

fun sum2(a: Int, b: Int): Int = a + b
fun sum3(a: Int, b: Int) = a + b

/*
fun sum4(a: Int, b: Int) {
    return a + b
}
*/

/**
 * 반환타입이 없는 함수는 Unit 을 반환한다
 */

fun printSum(a: Int, b: Int): Unit {
    println("$a + $b = ${a + b}")
}

/**
 * 디폴트 파라미터
 */

fun greeting(message:String ="안녕하세요!!") {
    println(message)
}

//fun main() {
//    greeting()
//    greeting("Hello!")
//}

/**
 * Named Argument
 * 함수를 실행시킬때, 해당 인자 = "값" 이런 식으로 할당 가능
 */
fun log(level: String = "INFO", message: String) {
    println("[$level]$message")
}

//fun main() {
//    log(message = "인포 로그")
//    log(level = "DEBUG", "디버그 로그")
//    log("WARN", "워닝 로그")
//    log(level = "ERROR", message = "에러 로그")
//}