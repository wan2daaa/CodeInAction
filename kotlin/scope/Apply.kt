package scope

/**
 * 수신 객체의 프로퍼티를 구성하고 수신객체의 결과를 그대로 반환하고 싶을 때 사용
 * 다시 프로퍼티를 초기화 하거나 수정해줄때
 *
 * apply 와 run 의 차이점은
 * apply 는 그 객체 그대로 반환 함
 * run 은 마지막 줄을 반환함
 */

fun main() {
    val client: DatabaseClient = DatabaseClient().apply {
        this.url = "localhost:3306" // this 도 작성가능하지만 생략가능
        username = "mysql"
        password = "1234"
        connect()
    }

    client.connect()

    println("client = $client")
}