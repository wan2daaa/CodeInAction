package advancedExceptionHandling

/**
 *
 * public inline fun <R> runCatching(block: () -> R): Result<R> {
 *     return try {
 *         Result.success(block())
 *     } catch (e: Throwable) {
 *         Result.failure(e)
 *     }
 * }
 *
 *
 * run catching 의 장점
 *
 *
 */

fun getStr() : Nothing = throw Exception("예외 발생 기본값으로 초기화")

fun main() {

    val result = try {
        getStr()
    } catch (e: Exception) {
        println(e.message)
        "기본값"
    }

    println("result = $result")

    println()
    //run catching 으로 변경후

    val resultWithRunCatching = runCatching { getStr() }
        .getOrElse {
        println(it.message)
        "기본값"
    }
    println("resultWithRunCatching = $resultWithRunCatching")

    println()


    val getOrNull = runCatching { "성공" }
        .getOrNull()
    println("getOrNull = $getOrNull")

    println()

    val resultWithExceptionOrNull: Throwable? = runCatching { getStr() }
        .exceptionOrNull()

    resultWithExceptionOrNull?.let {
        println(it.message)
//        throw it
    }

        println()

    val resultGetOrDefault = runCatching { getStr() }
        .getOrDefault("기본 값")

    println("resultGetOrDefault = $resultGetOrDefault")

    println()

    val resultGetOrElse = runCatching { getStr() }
        .getOrElse { //스코프 함수처럼 마지막 값을 반환
            println(it.message)

            "기본값"
        }

    println("resultGetOrElse = $resultGetOrElse")

//    val resultGetOrThrow = runCatching { getStr() }
//        .getOrThrow() // 에러를 던짐

    val resultGetOrThrow = runCatching { "성공" }
        .getOrThrow()

    println("resultGetOrThrow = $resultGetOrThrow")

    val resultWithMap = runCatching { "안녕" } //map의 리턴타입은 Success 라서 또 다른 함수 사용필요
        .map {
            "${it}하세요"
        }.getOrThrow()

    println("resultWithMap = $resultWithMap")

//    val resultMapWithGetOrDefault = runCatching { "안녕" } // map 내부에서 에러가 발생하면 뒤에 값을 복구할려고해도 복구 안됨
//        .map {
//            throw Exception("예외")
//        }.getOrDefault("기본값")

    //map 안에서 문제가 될 가능성이 있다고 하면 mapCatching 을 사용
    val resultMapCatching = runCatching { "안녕" } // map 내부에서 에러가 발생하면 뒤에 값을 복구할려고해도 복구 안됨
        .mapCatching {
            throw Exception("예외")
        }.getOrDefault("기본값")
    println("resultMapCatching = $resultMapCatching")



    val resultWithRecover = runCatching { "정상" }
        .recover { "복구" }
        .getOrNull()

    println("resultWithRecover = $resultWithRecover")

    val resultWithRecoverWithException = runCatching { getStr() }
        .recover {
            "복구"
        }.getOrNull()

    println("resultWithRecoverWithException = $resultWithRecoverWithException")

    val resultWithRecoverCatching = runCatching { getStr() }
        .recoverCatching {
            throw Exception("예외")
        }.getOrDefault("복구")

    println("resultWithRecoverCatching = $resultWithRecoverCatching")

}