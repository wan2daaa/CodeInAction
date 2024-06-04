package flowControl

fun main() {


    /**
     * 자바 코드를 코틀린의 when 식으로 변환한 코드
     */

    val day = 2

    val result = when (day) {
        1-> "월요일"
        2-> "화요일"
        3-> "수요일"
        4-> "목요일"
        5-> "금요일"
        6-> "토요일"
        7-> "일요일"
        else -> "기타"
    }
    println(result)

    /**
     * else 를 생략할 수 있다 (위의 케이스에서는 안되고 enum 같은 경우)
     */
    when (getColor()) {
        Color.RED -> println("빨강")
        Color.GREEN -> println("초록")
        Color.BLUE -> println("파랑")
    }

    when (getColor()) {
        Color.RED -> println("빨강")
        Color.GREEN -> println("초록")
        else -> println("파랑")
    }

    /**
     * 여러개의 조건을 콤마로 구분해서 한줄에 정의 가능
     */

    when (getNumber()) {
        0, 1 -> println("0 또는 1 임")
        else -> println("0 또는 1 아님")
    }


}

enum class Color {
    RED, GREEN, BLUE
}

fun getColor() : Color = Color.RED

fun getNumber() = 2
