
class Tokenizer(private val filename: String, private val lines: List<String>) {

    private val commentToken = '#'
    private val beginComment = '{'
    private val endComment = '}'
    private val stringDelim = '"'
    private val quote = '\''
    private val leftParen = '('
    private val rightParen = ')'

    private var lineNumber = 0

    private var iterator = 0

    private var commentLevel = 0

    fun tokenize() = TokenizerResult(filename, lines.map { tokenizeLine(it) }.reduce { acc, list -> acc + list })


    private fun tokenizeLine(line: String): List<Token> {
        ++lineNumber
        iterator = 0

        val tokens = mutableListOf<Token>()

        while (iterator < line.length) {

            if (line[iterator].isWhitespace()) {
                ++iterator
                continue
            }

            when (line[iterator]) {
                leftParen -> tokens.add(createToken(leftParen))
                rightParen -> tokens.add(createToken(rightParen))
                quote -> tokens.add(createToken(quote))
                stringDelim -> tokens.add(createToken(string(line)))
                commentToken -> comment(line)
                beginComment -> throw TokenizerError("asdf")
                else -> tokens.add(createToken(value(line)))
            }

        }


        return tokens
    }

    private fun string(line: String): String {

        var str = ""


        return str

    }

    private fun comment(line: String) {

    }

    private fun value(line: String): String {

        var value = ""

        return value

    }

    private fun createToken(token: String) = Token(token, lineNumber, iterator)
    private fun createToken(token: Char) = createToken(token.toString())

}

class Token(val token: String, val line: Int, val index: Int)

class TokenizerResult(val filename: String, val tokens: List<Token>)
