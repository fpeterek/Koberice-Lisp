import kotlin.math.max
import kotlin.math.min

private const val maxSurroundingChars: Int = 23

fun truncLine(line: String, index: Int): String {

    val pointerLine = " ".repeat(index) + '^' + " ".repeat(line.length - index - 1)

    var res: String
    var pointer: String

    if (index > maxSurroundingChars) {
        res = "..." + line.substring(index - maxSurroundingChars + 3, index)
        pointer = "   " + pointerLine.substring(index - maxSurroundingChars + 3, index)
    } else {
        res = line.substring(0, index)
        pointer = pointerLine.substring(0, index)
    }

    if (line.length > index + maxSurroundingChars) {
        res += line.substring(index, index + maxSurroundingChars - 2) + "..."
        pointer += pointerLine.substring(index, index + maxSurroundingChars - 2)
    } else {
        res += line.substring(index)
        pointer += pointerLine.substring(index)
    }

    return res + "\n" + decoratePointer(pointer)

}

fun decoratePointer(pointer: String): String {

    val builder = StringBuilder(pointer)

    val index = builder.indexOf('^')

    val leftBegin = max(index - 4, 0)
    for (i in leftBegin until index-1) {
        builder[i] = '~'
    }

    val rightBegin = index + 2
    for (i in rightBegin until min(pointer.length, rightBegin + 3)) {
        builder[i] = '~'
    }


    return builder.toString()

}


open class TokenizerError(message: String): Exception(message)

class MissingToken(token: String, file: String, lineIndex: Int, index: Int, line: String):
    TokenizerError(missingTokenMessage(token, file, lineIndex, index, line))

fun missingTokenMessage(token: String, file: String, lineIndex: Int, index: Int, line: String): String {

    val msg = "$file[$lineIndex[$index]]: Missing token $token"
    val line = truncLine(line, index)

    return msg + "\n" + line

}

class InvalidToken(message: String): TokenizerError(message)