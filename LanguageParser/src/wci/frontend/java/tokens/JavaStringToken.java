/*
	JavaStringToken.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source) throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal string token from the source.
     * @throws Exception if an error occurred.
     */
	@Override
    protected void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\"');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) 
			{
                currentChar = ' ';
            }

            // Escape
            if (currentChar == '\\') 
			{
				currentChar = nextChar();
				switch(currentChar)
				{
					case 't':
						textBuffer.append((char)9);
						valueBuffer.append((char)9);
						break;
						
					case 'n':
						textBuffer.append((char)10);
						valueBuffer.append((char)10);
						break;
						
					case 'r':
						textBuffer.append((char)13);
						valueBuffer.append((char)13);
						break;
						
					case '\"':
						textBuffer.append('\"');
						valueBuffer.append('\"');
						break;

					case '\\':
						textBuffer.append('\\');
						valueBuffer.append('\\');
						break;
						
					default:
						type = ERROR;
						value = INVALID_CHARACTER;
						text = textBuffer.toString();
						return;
				}	
				
				currentChar = nextChar();	// consume character
			}
			else
			{
				if (currentChar != EOF && currentChar != '\"')
				{
					textBuffer.append(currentChar);
					valueBuffer.append(currentChar);
					
					currentChar = nextChar();  // consume character
				}
			}
        } 
		while ((currentChar != '\"') && (currentChar != EOF));

        if (currentChar == '\"') 
		{
            nextChar();					// consume final quote
            textBuffer.append('\"');

            type = STRING_CONST;
            value = valueBuffer.toString();
			text = textBuffer.toString();
        }
        else 
		{
            type = ERROR;
            value = UNEXPECTED_EOF;
			text = textBuffer.toString();
        }
    }
}
