/*
	JavaCharacterToken.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;
import static wci.frontend.java.JavaErrorCode.INVALID_CHARACTER;
import static wci.frontend.java.JavaTokenType.*;

public class JavaCharacterToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCharacterToken(Source source) throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java character constant token from the source.
     * @throws Exception if an error occurred.
     */
	@Override
    protected void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
		char valueBuffer;
  
		char currentChar = nextChar();  // consume initial quote
		textBuffer.append('\'');

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
					valueBuffer = (char)9;
					break;

				case 'n':
					textBuffer.append((char)10);
					valueBuffer = (char)10;
					break;

				case 'r':
					textBuffer.append((char)13);
					valueBuffer = (char)13;
					break;

				case '\"':
					textBuffer.append('\"');
					valueBuffer = '\"';
					break;

				case '\\':
					textBuffer.append('\\');
					valueBuffer = '\\';
					break;

				default:
					type = ERROR;
					value = INVALID_CHARACTER;
					text = textBuffer.toString();
					return;
			}
		}
		else
		{
			if (Character.isLetterOrDigit(currentChar))
			{
				textBuffer.append(currentChar);
				valueBuffer = currentChar;
				currentChar = nextChar();
			}
			else
			{
				type = ERROR;
				value = INVALID_CHARACTER;
				text = textBuffer.toString();
				return;
			}
		}
		
		if (currentChar != '\'') 
		{
			type = ERROR;
			value = INVALID_CHARACTER;
			text = textBuffer.toString();
		}
		else
		{
			textBuffer.append('\'');

			type = CHARACTER_CONST;
			value = String.valueOf(valueBuffer);				
			text = textBuffer.toString();
			
			currentChar = nextChar();
		}
     }
}
