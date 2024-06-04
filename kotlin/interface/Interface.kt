package `interface`

/**
 * 코틀린 인터페이스에서는 인터페이스안에서 메소드를 작성가능하고, 프로퍼티도 있어도됨
 *
 * 인터페이스 여러개 상속 가능 !
 * 여러개 상속시 메소드가 여러개 중복되어 문제가 발생할 수 있음
 */

class Product(val name: String, val price: Int)

interface Wheel {
    fun roll()
}

interface Order {
    fun add(product: Product){ // 인터페이스 내에 디폴트 함수 사용 가능!
        println("${product.name} 주문이 완료되었습니다")
    }

    fun printId() = println("1234")

}

interface Body

interface Cart : Wheel, Body {

    var coin :Int


    val weight: String
        get() {
            //field 사용 불가!!
            return "20KG"
        }

    fun add(product: Product)

    fun rent() {
        if (coin > 0) {
            println("카트를 대여합니다.")
        }
    }

    fun test() {
        println("Test")
    }

    fun printId() = println("5678")
}

class MyCart(override var coin: Int) : Cart, Order{
    override fun add(product: Product) {
        if (coin <= 0) println("코인을 넣어주세요")
        else println("${product.name}이(가) 상품에 추가됐습니다.")

        super<Order>.add(product)
    }

    override fun printId() {
        super<Cart>.printId()
        super<Order>.printId()
    }


    override fun roll() {
        println("카트가 굴러갑니당~")
    }
}

fun main() {
    val cart = MyCart(coin = 100)

    cart.rent()
    cart.add(Product(name = "장난감", price = 1000))

    cart.test()
    println("cart.coin = ${cart.coin}")
    println("cart.weight = ${cart.weight}")


    val noCoinCart = MyCart(coin = 0)
    noCoinCart.rent()
    noCoinCart.add(Product(name = "장난감", price = 100))

    cart.roll()

    cart.printId()
}