/*
	JavaCommentToken.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;

public class JavaCommentToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCommentToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java comment token from the source.
     * @throws Exception if an error occurred.
     */
	@Override
    protected void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        textBuffer.append("//");

        // Get all comment
		char currentChar = nextChar();
        while ((currentChar != '\r' && currentChar != '\n' && currentChar != EOF))
		{
			textBuffer.append(currentChar);
			valueBuffer.append(currentChar);
 			currentChar = nextChar();
		}

        type = COMMENT;
        value = valueBuffer.toString();  
        text = textBuffer.toString();
    }
}
