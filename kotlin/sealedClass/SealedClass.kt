package sealedClass

/**
 * 실드 클래스는
 * 하나의 상위클래스 또는 인터페이스에서 하위 클래스에 대한 정의를 제한 할 수 있는 방법
 *
 * 실드 클래스로 상속 받은 클래스를 제한해서 when 등에서 사용할 때 else 를 작성할 필요가 없다
 * 다른 패키지 다른 모듈에 있으면 안된다
 *
 * else 를 없앰으로써 다른 구현체를 못 넣는 실수 등을 없앨 수 있어서 유리하다
 */

// abstract class Developer {
 sealed class Developer {
     abstract val name: String
    abstract fun code(language: String)

 }

data class BackendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("저는 백엔드 개발자입니다 $language 를 사용합니다")
    }

}

data class FrontendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("저는 프론트엔드 개발자입니다 $language 를 사용합니다")
    }

}

object OtherDeveloper : Developer() {

    override val name: String = "익명"

    override fun code(language: String) {
        TODO("Not yet implemented")
    }

}

object DeveloperPool {
    val pool = mutableMapOf<String, Developer>()

    fun add(developer: Developer) = when (developer) {
        is BackendDeveloper -> pool[developer.name] = developer
        is FrontendDeveloper -> pool[developer.name] = developer
        is OtherDeveloper -> println("지원하지않는 개발자종류입니다")
//        else -> {
//            println("지원하지 않는 개발자입니다.")
//        }
    }

    fun get(name: String) = pool[name]
}

fun main() {
    val backendDeveloper = BackendDeveloper(name = "와니")
    DeveloperPool.add(backendDeveloper)

    val frontendDeveloper = FrontendDeveloper(name = "갱주")
    DeveloperPool.add(frontendDeveloper)

    println(DeveloperPool.get("와니"))
    println(DeveloperPool.get("갱주"))
}