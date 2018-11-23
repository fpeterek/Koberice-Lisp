

class KobericeLisp {

    private fun read(name: String): List<String> {
        val reader = Reader()
        return reader.read(name)
    }

    private fun tokenize(filename: String, code: List<String>): TokenizerResult {
        val tokenizer = Tokenizer(filename, code)
        return tokenizer.tokenize()
    }

    private fun lex(tokenizerResult: TokenizerResult): List<Lexeme> {
        val lexer = Lexer(tokenizerResult)
        return lexer.lex()
    }

    private fun parse(lexemes: List<Lexeme>): AbstractSyntaxTree {
        val parser = Parser(lexemes)
        return parser.parse()
    }


    public fun run(filename: String) {
        val ast = parse(lex(tokenize(filename, read(filename))))
    }


}