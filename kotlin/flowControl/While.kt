package flowControl

fun main() {

    /**
     * 자바의 While 문이랑 동일
     * 조건을 확인하고 참이면, 코드블록을 실행한 후 다시 조건을 확인
     */

    var x = 5

    while (x > 0) {
        println(x)
        x--
    }
}