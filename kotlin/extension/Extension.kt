package extension

/**
 * 확장 함수
 * 코틀린은 디자인패턴, 상속 사용하지 않아도 확장할 수있다
 * 일반적으로 확장할 수 없는 Kotlin 의 표준라이브러리에 커스텀 기능을 추가할 수 있다
 *
 * fun 확장할클래스.함수명
 *
 * 확장 프로퍼티도 가능!!
 */

/**
 * 리시버, 수신자 객체
 *
 * fun String.first(): Char {
 *     return this[0]
 * }
 * 이 메소드에서 this 는 실제 확장이 되는 대상의 참조임
 *  main 에서 "ABCD"가 됨
 *
 *  조심해야할분
 *
 *  확장하는 클래스에 같은 이름의 메서드가 있다면 클래스 내의 멤버 메서드가 우선됨
 *
 *  그 클래스가 null 이여도 사용할 수있게 확장함수를 만들 수 있다.
 */


fun String.first(): Char {
    return this[0]
}

fun String.addFirst(char: Char): String {
    return char + this.substring(0)
}

class MyExample {
    fun printMessage() = println("클래스 출력")
}

fun MyExample.printMessage() = println("확장 출력")

fun MyExample?.printNullOrNotNull() {
    if (this == null) println("널임")
    else println("널이 아님")
}

fun main() {
    println("ABCD".first())

    println()

    println("ABCD".addFirst('Z'))

    MyExample().printMessage()

    println()

    var myExample: MyExample? = null
    myExample.printNullOrNotNull()

    myExample = MyExample()
    myExample.printNullOrNotNull()


}