/*
	JavaSpecialSymbolToken.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

public class JavaSpecialSymbolToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaSpecialSymbolToken(Source source) throws Exception
    {
        super(source);
    }

    /**
     * Extract a Pascal special symbol token from the source.
     * @throws Exception if an error occurred.
     */
	@Override
    protected void extract() throws Exception
    {
        char currentChar = currentChar();

        text = Character.toString(currentChar);
        type = null;
		value = null;

        switch (currentChar) 
		{
			case '!':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '=':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				else if(currentChar == '<' || currentChar == '>')
				{
					text = Character.toString(currentChar) + text.charAt(0);
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;
	
			case '+':
				currentChar = nextChar();
				if(currentChar == '+' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '-':
				currentChar = nextChar();
				if(currentChar == '-' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;
				
			case '/':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;
				
			case '*':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '%':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '<':
				currentChar = nextChar();
				if(currentChar == '<' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '>':
				currentChar = nextChar();
				if(currentChar == '>' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
					
					if(currentChar == '>')
					{
						text += currentChar;					
						currentChar = nextChar();
						
						if(currentChar == '=')
						{
							text += currentChar;					
							currentChar = nextChar();
						}
					}
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;
				
			case '|':
				currentChar = nextChar();
				if(currentChar == '|' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '&':
				currentChar = nextChar();
				if(currentChar == '&' || currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case '^':
				currentChar = nextChar();
				if(currentChar == '=')
				{
					text += currentChar;					
					currentChar = nextChar();
				}
				type = SPECIAL_SYMBOLS.get(text);
				break;

			case ',':
			case '.':
			case ':':
			case ';':
			case '?':
			case '(':
			case ')':
			case '[':
			case ']':
			case '{':
			case '}':
			case '@':
				currentChar = nextChar();
				type = SPECIAL_SYMBOLS.get(text);
				break;
 
            default:
                type = ERROR;
                value = INVALID_CHARACTER;
				break;
        }
    }
}
