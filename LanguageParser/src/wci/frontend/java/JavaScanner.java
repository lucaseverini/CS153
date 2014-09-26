/*
	JavaScanner.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java;

import wci.frontend.*;
import wci.frontend.java.tokens.*;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaErrorCode.*;

public class JavaScanner extends Scanner
{
    /**
     * Constructor
     * @param source the source to be used with this scanner.
     */
    public JavaScanner(Source source)
    {
        super(source);
    }

    /**
     * Extract and return the next Java token from the source.
     * @return the next token.
     * @throws Exception if an error occurred.
     */
	@Override
    protected Token extractToken() throws Exception
    {
        skipWhiteSpace();

        Token token;
        char currentChar = currentChar();

        // Construct the next token.  The current character determines the
        // token type.
        if (currentChar == EOF) {
            token = new EofToken(source);
        }
        else if (Character.isLetter(currentChar)) {
            token = new JavaWordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new JavaNumberToken(source);
        }
        else if (currentChar == '\"') {
            token = new JavaStringToken(source);
        }
        else if (currentChar == '\'') {
            token = new JavaCharacterToken(source);
        }
		else if(currentChar == '/')
		{
			char nextChar = source.peekChar();
			if(nextChar == '/')
			{
				nextChar();
				token = new JavaCommentToken(source);
			}
			else if(nextChar == '*')
			{
				nextChar();
				token = new JavaCommentBlockToken(source);
			}
			else
			{
				token = new JavaSpecialSymbolToken(source);
			}
		}
        else if (JavaTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) 
		{
            token = new JavaSpecialSymbolToken(source);
        }
        else {
            token = new JavaErrorToken(source, INVALID_CHARACTER, Character.toString(currentChar));
            nextChar();  // consume character
        }

        return token;
    }

    /**
     * Skip whitespace characters by consuming them.  A comment is whitespace.
     * @throws Exception if an error occurred.
     */
    private void skipWhiteSpace() throws Exception
    {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar)) 
		{
            currentChar = nextChar();  // consume whitespace character
		}
    }
}
