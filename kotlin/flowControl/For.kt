package flowControl

fun main() {

    /**
     * 범위 연산자 .. 를 이용해서 for loop 를 돌릴 수 있다. ( 0 .. 1 == 0 <= <= 1)
     */
    for (i in 0..3) {
        println("i = $i")
    }

    println()

    /**
     * 범위 연산자 until 을 이용해서 for loop 를 돌릴 수 있다. ( 0 until 1 == 0 <= < 1)
     * 뒤에 온 숫자는 포함 X
     */
    for (j in 0 until 3) {
        println("j = $j")
    }

    println()

    /**
     * step 에 들어온 값 만큼 증가 시킨다 (.. until)두개다 가능
     */
    for (k in 0..6 step 2) {
        println("k = ${k}")
    }
//    for (k in 0 until 6 step 2) {
//        println("k = ${k}")
//    }

    println()
    /**
     * downTo를 사용해서 반복해서 값을  감소시킨다.
     */
    for (l in 6 downTo 2 step 3) {
        println("l = ${l}")
    }

    println()

    /**
     *  자바처럼 전달받은 배열을 반복가능
     */
    val numbers = arrayOf(1, 2, 3)

    for (eachNumber in numbers) {
        println("eachNumber = $eachNumber")
    }


}