package flowControl

fun main() {

    /**
     * if..else
     */
    val job = "Backend Developer"

    if (job == "Backend Developer") {
        println("백엔드 개발자")
    } else {
        println("프론트엔드 개발자")
    }

    /**
     * 코틀린의 if...else는 표현식이다
     */
    val age: Int = 27

    val str = if (age >= 20) {
        "성인"
    } else {
        "아이"
    }
    println(str)

    /**
     * 코틀린은 삼항연산자가 없다.
     * 대신 if..else 가 표현식이므로 불필요하다
     */
    val a = 1
    val b = 2
    val c = if(b > a) b else a
    println("c = $c")

}