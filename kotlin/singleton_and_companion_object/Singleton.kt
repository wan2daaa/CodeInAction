package singleton_and_companion_object

import java.time.LocalDateTime

/**
 * 이 object 키워드만 사용해도 싱글톤으로 사용가능
 *
 * 자바의 static 같은 기능으로 사용가능
 *
 * const 라는 키워드로 변수를 선언하면 자바의 상수와 같다!!
 *
 * utility 클래스로 활용하거나
 * 상수들의 집합으로써 사용해도 좋을듯
 *

 *
 */
object Singleton {

    val a = 1234

    fun printA() = println(a)

}

object DatetimeUtils {
    val now : LocalDateTime
        get() = LocalDateTime.now()

    const val DEFAULT_FORMAT = "YYYY-MM-DD"

    fun same(a: LocalDateTime, b: LocalDateTime): Boolean {
        return a == b
    }
}

/**
 * 동반객체
 * companion object 라는 키워드를 사용해서 클래스 내부에서 생성되는 객체
 *
 * 동반객체는 자기 자신의 이름을 가질 수 있다 (MyCompanion)
 */
class MyClass private constructor() {


    companion object MyCompanion{
     val a = "myClass"

     fun newInstance() = MyClass()
 }

}

fun main() {

    println(Singleton.a)
    Singleton.printA()

    println(DatetimeUtils.now)
    println(DatetimeUtils.now)
    println(DatetimeUtils.now)


    println(DatetimeUtils.DEFAULT_FORMAT)

    val now = LocalDateTime.now()
    println(DatetimeUtils.same(now, now))

    println()

    println(MyClass.a)
    println(MyClass.newInstance())

    println(MyClass.MyCompanion.a)
    println(MyClass.MyCompanion.newInstance())

}