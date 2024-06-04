package scope

import extension.addFirst

/**
 * 결과 반환 없이 내부에서 수신 객체를 통해 다른 함수를 호출하고 싶을 때 사용
 */

fun main() {

    var str = "HEllo"

    val length: Int = with(str) {
        println("length = $length")
        println(this.addFirst('a'))
        length
    }

    println("length = $length")

}