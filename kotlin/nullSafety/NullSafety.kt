package nullSafety

fun main() {

//    val a: String = null // null 로 초기화 하면 컴파일 오류 발생
    var b: String = "ASD"
//    b = null // 컴파일 오류 발생 null 을 못받는 타입

    var c : String? = null // ? 로 Nullable 한 타입이라고 코틀린 컴파일러에게 알려줌
//    a.length //nullable 하면 컴파일 에러 발생
    println(c?.length) // null 이 출력됨

    val d: Int = if (c != null) c.length else 0
    println(d)

    val e = c?.length ?: 0 // ?: <- 좌변이 null 인경우 우변의 값을 반환
    println(e)

    val nullableValue : String? = null
    val isNotNull = nullableValue!!.length // !!로 무조건 널이 아니라고 단언해주는 연산자

}