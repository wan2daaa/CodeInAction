package generics

class MyGenerics<out T>(val t: T) {}

class Bag<T> {

    fun saveAll(
        to: MutableList<in T>,
        from : MutableList<T>
    ): MutableList<in T> {
        to.addAll(from)
        return to
    }

}

fun main() {

    /**
     * 제네릭을 사용한 클래스의 인스턴스를 만들려면 타입 argument 를 제공해줌
     * 파라미터로 타입을 추론가능한 경우에는 생략할수도 있음
     */
    val generics = MyGenerics<String>(t = "테스트")
    val genericsWithoutTypeArgument = MyGenerics(t = "테스트")

    //변수의 타입에 제네릭을 사용한 경우
    val list1: MutableList<String> = mutableListOf()

    //타입 argument 로 생성자에서 추가
    val list2 = mutableListOf<String>()

    // stop projection
    val list3: List<*> = listOf<String>("테스트")
    val list4: List<*> = listOf<Int>(1, 2, 3, 4)

    // 변성
    //제네릭에서 파라미터화된 타입이 서로 어떤관계에 있는지
    // 공변성, 반공변성, 무공변성으로 나뉘어짐
    // 어떤걸 선택할지는 (effective java) PECS 를 사용
    //PECS는 Producer-Extends, Consumer-Super
    //공변성은 자바 제네릭의 extends 코틀린에선 out
    //반공변성은 자바 제네릭의 super 코틀린에선 in

//    String 의 상위 인터페이스인 CharSequence 타입으로 할당이 될것 같지만 안돼!!
//    하위클래스와 상위클래스간의 유연한 형태가 필요할 때가 있음
    val generic = MyGenerics<String>(t = "테스트")

    val charGenerics: MyGenerics<CharSequence> = generic

//  반공변성을 사용해야함 Consumer 제네릭을 사용하려는 측 즉 반 공변성을 사용하기 위해서 파라미터에 in 추가
    val bag = Bag<String>()

    println(bag.saveAll(mutableListOf<CharSequence>("1", "2"), mutableListOf<String>("3", "4")))


}