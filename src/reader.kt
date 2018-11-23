import java.io.File

class Reader {

    fun read(filename: String): List<String> {
        val file = File(filename)
        return file.readLines()
    }

}