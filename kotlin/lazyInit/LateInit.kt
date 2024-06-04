package lazyInit

/**
 * late init
 * 필요한경우는 가변 프로퍼티에 대한 지연 초기화가 필요한 경우
 * 의존성 주입 (DI)
 *
 * 타입은 항상 nonNull 타입
 *
 * Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property text has not been initialized
 *
 * 초기화 확인 여부 isInitialized <- 클래스 내부에서만 사용가능 !
 *
 * lazy init
 * 항상 불변 프로퍼티로 유지해야함
 */

class LateInit() {

    lateinit var text: String

    val textInitialized: Boolean
        get() = this::text.isInitialized

    fun printText() {
        if (this::text.isInitialized) {
            println("초기화됨")
        } else {
            text = "안녕하세요"
        }
        println(text)
    }
}

fun main() {
    val test = LateInit()

    println("test.textInitialized = ${test.textInitialized}")

//    test.text = "hello"
    test.printText()
}