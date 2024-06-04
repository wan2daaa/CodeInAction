/**
 * 코틀린에서는 class 에서 constructor 라는 키워드를 사용 가능
 *
 * constructor 키워드를 생략하고 사용가능 !
 *
 * 프로퍼티 안에서 후행 쉼표가능! (자바스크립트처럼)
 */
class Coffee constructor(
    var name: String = "",
    var price: Int = 0,
    var iced: Boolean = false,
) {

    val brand : String
        get() = "스타벅스"

    val test : String
        get() {
            return "test"
        }

    var quantity: Int = 0
        set(value) {
            if (value > 0) { //수량이 0 이상인 경우에만 할당
                field = value //field 는 getter, setter 에 사용되는 식별자를 사용해서 실제 필드의 참조에 접근함!
                              // field 대신 quantity 로 접근하게 되면 계속 무한 반복 되면서 StackOverFlowError 발생!
            }
        }
}

class CoffeeWithoutConstructorKeyword(val name: String) {
}


/**
 * 본문 내용 없는 class도 생성 가능
 */
class EmptyClass

fun main() {
    var coffee = Coffee()

    // setter
    coffee.name = "아이스 아메리카노"
    coffee.price = 2000
    coffee.quantity = 1
    coffee.quantity = -10

    coffee.iced = true

    if (coffee.iced) { //클래스의 프로퍼티는 객체지향적이다! 자바는 메소드로 상태를 나타낼 수 밖에 없어서 !
        println("아이스커피")
    }
    println("${coffee.brand} ${coffee.name} 가격은 ${coffee.price} 수량은 ${coffee.quantity} ")
}