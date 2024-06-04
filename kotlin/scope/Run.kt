package scope

/**
 * 수신 객체 프로퍼티 구성하거나 새로운 결과를 반환하고 싶을 때
 */

class DatabaseClient {
    var url: String? = null
    var username: String? = null
    var password: String? = null

    //DB에 접속하고 Boolean 결과를 반환
    fun connect(): Boolean {
        println("DB 접속중...")
        Thread.sleep(1000)
        println("DB 접속완료")
        return true
    }
}

fun main() {
    /*

        val config = DatabaseClient()
        config.url = "localhost:3306"
        config.username = "mysql"
        config.password = "1234"
        val connected = config.connect()

        println("connected = $connected")
    */

    //run을 활용해서 작성
    //마지막이 run의 return 값으로 됨
    val connected = DatabaseClient().run {
        this.url = "localhost:3306" // this 도 작성가능하지만 생략가능
        username = "mysql"
        password = "1234"

        connect()
    }

    println(connected)

    // 참고 let 활용
//    val connected: Boolean = DatabaseClient().let {
//        it.url = "localhost:3306"
//        it.username = "mysql"
//        it.password = "1234"
//
//        it.connect()
//    }

    // 참고 with 사용
//    val connectedWithWith = with(DatabaseClient()) {
//        this.url = "localhost:3306" // this 도 작성가능하지만 생략가능
//        username = "mysql"
//        password = "1234"
//        connect()
//    }
//
//    println("connectedWithWith = ${connectedWithWith}")

}
