/*
	JavaErrorHandler.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java;

import wci.frontend.*;
import wci.message.Message;
import static wci.frontend.java.JavaErrorCode.*;
import static wci.message.MessageType.SYNTAX_ERROR;

public class JavaErrorHandler
{
    private static final int MAX_ERRORS = 100;
    private static int errorCount = 0;			// count of syntax errors

    /**
     * Getter.
     * @return the syntax error count.
     */
    public int getErrorCount()
    {
        return errorCount;
    }

    /**
     * Flag an error in the source line.
     * @param token the bad token.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void flag(Token token, JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners.
		Message msg = new Message(SYNTAX_ERROR, new Object[] {
									token.getLineNumber(), token.getPosition(), token.getText(), errorCode.toString() });
        parser.sendMessage(msg);

        if (++errorCount > MAX_ERRORS) 
		{
            abortTranslation(TOO_MANY_ERRORS, parser);
        }
    }

    /**
     * Abort the translation.
     * @param errorCode the error code.
     * @param parser the parser.
     */
    public void abortTranslation(JavaErrorCode errorCode, Parser parser)
    {
        // Notify the parser's listeners and then abort.
        String fatalText = "FATAL ERROR: " + errorCode.toString();
        parser.sendMessage(new Message(SYNTAX_ERROR, new Object[] {0, 0, "", fatalText}));
        System.exit(errorCode.getStatus());
    }
}
