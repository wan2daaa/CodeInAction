package advancedExceptionHandling

import java.io.FileWriter

/**
 * 자바의 try-with-resources 를 대체하는 use
 */
fun main() {


    FileWriter("test.txt")
        .use {
            it.write("Hello Kotlin")
        }
}