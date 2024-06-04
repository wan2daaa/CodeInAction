package enum

/**
 * 자바 enum에서 property 같이 enum 생성자? 부분에 작성하면 됨
 *
 * 메소드도 추가 가능
 * 인터페이스나 추상 클래스처럼 각각의 클래스에서 구현할 수 있게 할 수 있음
 *
 * 메서드를 interface 에서 상속받아서 사용가능
 *
 * entries , values() 를 이용해서 모든 enum 값을 가져올 수 있음
 */

enum class PaymentStatus(val label: String): Payable {
    UNPAID("미지급") {
        override fun isPayable(): Boolean = true
    },
    PAID("지급완료") {
        override fun isPayable(): Boolean = false
    },
    FAILED("지급실패") {
        override fun isPayable(): Boolean = false
    },
    REFUNDED("환불") {
        override fun isPayable(): Boolean = false
    },
    ;

    fun test() {
        println(this.name)
    }
//    abstract fun isPayable(): Boolean
}

interface Payable {
    fun isPayable() : Boolean
}

fun main() {
    println(PaymentStatus.UNPAID.label)

    if (PaymentStatus.UNPAID.isPayable()) {
        println("결제 가능 상태")
    }

    PaymentStatus.UNPAID.test()

    val paymentStatus = PaymentStatus.valueOf("PAID")
    println("paymentStatus.label = ${paymentStatus.label}")

    if (paymentStatus == PaymentStatus.PAID) {
        println("지급완료 일치합니다 !")
    }

//    for (status in PaymentStatus.values()) {
    for (status in PaymentStatus.entries) {
        print("$status.name = ${status.name} ")
        println("$status.ordinal = ${status.ordinal}")
    }
}