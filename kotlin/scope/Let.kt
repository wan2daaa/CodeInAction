package scope

// 기본적으로 null 이 아닌 경우 사용될 로직을 사용하고
// 이에 따른 새로운 결과를 반환하고 싶을 때 사용함

/**
 * 안전 연산자와 같이 사용하고, null 이 아닌 경우에만 작동됨!
 *
 * 마지막 값이 반환됨
 *
 * let 안에서 let 이 사용되는 경우도 많음
 * 가독성 면에서는 좋지 않음
 * let 을 사용한 것보다 if else 가 더 좋을 수도 있음
 */
fun main() {

    val str: String? = null

    str?.let {
        println(it)
    }

    val notNullStr : String? = "안뇽"

    val result:Int? = notNullStr?.let {
        println(it)

        1234
    }

    println(result)


    val abc: String? = "abc"
    abc?.let {

        val def : String? = "def"
        def?.let {
            println("abcdef가 null 이 아님")
        }

        123123
    }

    abc?.let {

        val def : String = "def"
        if (!abc.isNullOrEmpty() && !def.isNullOrEmpty()) {
            println("abcdef가 null이 아님")
        }
    }

    val hello = "hello"
    val hi = "hi"

    hello.let { a: String ->
        println(a.length)

        hi.let { b->
            println(b.length)
        }
    }

}