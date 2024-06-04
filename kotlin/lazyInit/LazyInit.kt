package lazyInit

/**
 * 아래 코드의 문제점
 * var 선언 : 가변 변수로써 위험함
 *
 * 하지만 지연 할당을 쓰기 위해서 어쩔수 없이 가변 변수로 사용
 *
 * by lazy 를 사용해서 아래 같은 문제점 해결 가능
 *
 * 사용 시점에 첫번째만 초기화됨
 *
 * 멀티쓰레드 환경에서 안전하게 동작함
 *
 * 멀티쓰레드 안전을 설정할 수있음 기본은 LazyThreadSafetyMode.SYNCHRONIZED
 * 멀티 쓰레드 환경이 아닌 경우 syncronized 자체가 오버헤드가 발생할 수 있음
 *
 * 멀티 쓰레드 환경이여도 동기화 필요없을 때 PUBLICATION 사용 (아래와 같이 딱히 변경될 여지 없는 경우 굳이 오버헤드 만들 필요가 없기 때문)
 *
 */
class HelloBot {
    var greeting: String? = null

//    val greetWithLazy : String by lazy(LazyThreadSafetyMode.NONE) {
    val greetWithLazy : String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        println("초기화 로직 시행")
        getHello()
    }

    fun sayHello() = println(greeting)
    fun sayHelloWithLazy() = println(greetWithLazy)

}

fun getHello() = "안녕하세요"

fun main() {

    val helloBot = HelloBot()

    // ... 특정 로직이 있다고 가정

//    helloBot.greeting = getHello()
//    helloBot.sayHello()

//    helloBot.sayHelloWithLazy()
    /**
     * 초기화는 처음 한번 실행되고 다음엔 실행 안됨
     */
//    helloBot.sayHelloWithLazy()
//    helloBot.sayHelloWithLazy()

    /**
     * 여러 쓰레드를 돌아도 초기화는 한번만 진행됨
     */
    for (i in 1..5) {
        Thread {
        helloBot.sayHelloWithLazy()
        }.start()
    }

}