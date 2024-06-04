package scope

/**
 * 부수 작업을 진행하거나 결과값을 그대로 반환하고 싶을 때
 *
 * 조금더 클래스내 메소드를 여러개 사용할 때 조금더 가독성 높일 수 있음
 */
class User(val name: String, val password: String) {

    fun validate() {
        if (name.isNotEmpty() && password.isNotEmpty()) {
            println("검증 성공!")
        } else {
            println("검증 실패!")
        }
    }

    fun printName() = println(name)

}

fun main() {

    val user: User = User(name = "wane", password = "1234")
    user.validate()

    User(name = "wane", password = "1234").also {
        it.validate()
        it.printName()
    }
}