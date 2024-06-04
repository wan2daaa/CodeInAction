package implement


/**
 * 코틀린에서는 기본적으로 class 는 자바에서 final class 처럼 상속이 막혀있음
 * open 키워드를 통해 상속 가능하게 가능
 * 코틀린에서 함수나 프로퍼티를 재정의할 때도 open 키워드를 사용함!
 *
 * 프로퍼티는 기본 생성자로 재정의 가능
 *
 * 클래스를 상속해서 override를 하면 기본적으로 모든 프로퍼티와 메소드를 가져오는데
 * 상속할때 프로퍼티랑 메소드를 override 로 수정하게 하기 싫으면 앞에 final 키워드를 붙이면 됨
 */
open class Dog {
    open var age: Int = 0

    open fun bark() {
        println("멍멍")
    }
}

open class Bulldog : Dog() {
    override var age: Int = 0

    override fun bark() {
        println("컹컹")
    }
}

class AnotherDog(override var age: Int = 0) : Dog() {

    override fun bark() {
        println("멍 ! 멍 !")
    }
}

open class FinalChildBullDog : Bulldog() {
    final override var age: Int = 0

    final override fun bark() {
        super.bark()
    }
}

// age, bark() 상속 안됨!
class OtherChildBullDog : FinalChildBullDog() {
//    override var age : Int = 0

//final override fun bark() {
//    super.bark()
//}
}

abstract class Developer {
    abstract var age: Int
    abstract fun code(language: String)
}

class BackendDeveloper(override var age: Int) : Developer() {
    override fun code(language: String) {
        println("I code with $language")
    }

}


fun main() {

    val dog = Dog()
    dog.bark()

    val bulldog = Bulldog()
    bulldog.bark()

    val otherDog = AnotherDog(age = 2)
    println("otherDog.age = ${otherDog.age}")
    otherDog.bark()

    val childBulldog = FinalChildBullDog()
    childBulldog.bark()

    val otherBulldog = OtherChildBullDog()
    otherBulldog.bark()


    val backendDeveloper = BackendDeveloper(age = 20)
    println("backendDeveloper.age = ${backendDeveloper.age}")
    backendDeveloper.code("Kotlin")



}