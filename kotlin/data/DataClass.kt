package data

/**
 * 데이터 클래스
 * 데이터를 보관하거나 전달하는 객체를 만들때 사용
 * e.x. dto
 * 기본 메소드
 * -> equals, hashcode, to String, componentN , copy
 *
 * java record 와 유사
 *
 * 필요한 이유
 * 데이터를 저장하는 목적으로 하는 클래스이기때문에 필수적인 함수(toString, equals, hashcode)등을 알아서 재정의해주고
 *
 * 프로퍼티를 var 로 변경해서 하면 불변성을 지키기 어려워서 val 추천
 * 이 방법보다 copu() 메소드를 적극 활용하자!!
 *
 * conponentN : 프로퍼티를 순서대로 가져오거나 구조분해 할당으로도 가능
 */

data class Person(var name: String, val age: Int)

class ClassPerson(val name:String, val  age: Int)

fun main() {

    //객체 동등성 비교
    val classPerson1 = ClassPerson(name = "wane", age = 27)
    val classPerson2 = ClassPerson(name = "wane", age = 27)

    println("classPerson1 = $classPerson1")
    println("classPerson2 = $classPerson2")

    println("classPerson1==classPerson2 = ${classPerson1 == classPerson2}")

    println("classPerson1.hashCode() = ${classPerson1.hashCode()}")
    println("classPerson2.hashCode() = ${classPerson2.hashCode()}")

    println()

    val person1 = Person(name = "wane", age = 27)
    val person2 = Person(name = "wane", age = 27)

    println("person1 = $person1")
    println("person1 = $person2")

    println("person1==person2 = ${person1 == person2}")

    println("person1.hashCode() = ${person1.hashCode()}")
    println("person2.hashCode() = ${person2.hashCode()}")

    val set = hashSetOf(person1)
    println(set.contains(person2))

    println()

    person1.name = "strange"
    println("person1==person2 = ${person1==person2}")


    val copyPerson = person1.copy(name = "hohoho!")
    println("copyPerson = ${copyPerson}")

    println("person1 : 이름= ${person1.component1()}, 나이=${person1.age}")

    val (name, age) = person1
    println("구조분해할당 : 이름=$name, 나이=$age")

}