/*
	JavaTokenType.java

	By Luca Severini

	September-25-2014
*/

package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;
import wci.frontend.TokenType;

public enum JavaTokenType implements TokenType
{
    // Reserved words
	ABSTRACT("abstract"), NEW("new"), DEFAULT("default"), PACKAGE("package"), SYNCHRONIZED("synchronized"), THIS("this"),
	CLASS("class"), OBJECT("objec"), PUBLIC("public"), PRIVATE("private"), PROTECTED("protected"), EXTENDS("extends"),
    IMPLEMENTS("implements"), SWITCH("switch"), CONST("const"), DO("do"), ELSE("else"), END("end"), THROWS("throws"),
    FOR("for"), GOTO("goto"), IF("if"), IN("in"), NULL("null"), BREAK("break"), TO("to"), WHILE("while"), THROW("throw"),
    CASE("case"), CONTINUE("continue"), INT("int"), LONG("long"), CHAR("char"), FLOAT("float"), SIZE_OF("sizeof"),
	DOUBLE("double"), BOOLEAN("boolean"), TRUE("true"), FALSE("false"), STATIC("static"), VOID("void"), IMPORT("import"),
	ENUM("enum"), INSTANCEOF("instanceof"), RETURN("return"), TRANSIENT("transient"), TRY("try"), CATCH("catch"),
	FINAL("final"), INTERFACE("interface"), FINALLY("finally"), VOLATILE("volatile"), SUPER("super"), NATIVE("native"), 
	SHORT("short"), STRICT("strictfp"),
	
	// Annotations
	ANNOT_OVERRIDE("@Override"), ANNOT_DEPRECATED("@Deprecated"), ANNOT_SUPPRESSWARNINGS("@SuppressWarnings"),
	ANNOT_SAFEVARARGS("@SafeVarargs"), ANNOT_FUNCTIONALINTERFACE("@FunctionalInterface"), ANNOT_RETENTION("@Retention"), 
	ANNOT_DOCUMENTED("@Documented"), ANNOT_TARGET("@Target"), ANNOT_INHERITED("@Inherited"), ANNOT_REPEATABLE("@Repeatable"),

    // Special symbols
    INCREMENT("++"), PLUS("+"), MINUS("-"), DECREMENT("--"), MULTIPLY("*"), ASSIGN("="),
    DOT("."), COMMA(","), SEMICOLON(";"), COLON(":"), QUOTE("'"), NOT("!"), MOD("%"), AND("&"), OR("|"), 
    EQUAL("=="), NOT_EQUAL("!="), LESS_THAN("<"), LESS_EQUAL("<="), DIVIDE("/"), DOUBLE_AND("&&"), DOUBLE_OR("||"),
    GREATER_EQUAL(">="), GREATER_THAN(">"), SMALLER_THAN("<"), LEFT_PAREN("("), RIGHT_PAREN(")"), 
	SHIFT_RIGHT(">>"), SHIFT_LEFT("<<"),
	MINUS_ASSIGN("-="), PLUS_ASSIGN("+="), MULTIPLY_ASSIGN("*="), DIVIDE_ASSIGN("/="),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"), QUESTION("?"), EOR("^"), COMPLEMENT("~"),
	MOD_ASSIGN("%="), AND_ASSIGN("&="), EOR_ASSIGN("^="), OR_ASSIGN("|="), SHIFT_LEFT_ASSIGN("<<="), SHIFT_RIGHT_ASSIGN(">>="),
	UNSIGN_SHIFT_RIGHT(">>>"), UNSIGN_SHIFT_RIGHT_ASSIGN(">>>="), ANNOT("@"),

    IDENTIFIER, BOOLEAN_CONST, CHARACTER_CONST, INTEGER_CONST, LONGINTEGER_CONST, SHORT_CONST, 
	STRING_CONST, REAL_CONST, COMMENT, ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX  = STRICT.ordinal();

    private static final int FIRST_SPECIAL_INDEX = INCREMENT.ordinal();
    private static final int LAST_SPECIAL_INDEX  = ANNOT.ordinal();

    private final String text;  // token text

    /**
     * Constructor
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor
     * @param text the token text
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter
     * @return the token text
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Pascal reserved word text strings
    public static final HashSet<String> RESERVED_WORDS = new HashSet<>();
    static 
	{
        JavaTokenType values[] = JavaTokenType.values();
        for (int idx = FIRST_RESERVED_INDEX; idx <= LAST_RESERVED_INDEX; ++idx) 
		{
            RESERVED_WORDS.add(values[idx].getText().toLowerCase());
        }
    }

    // Hash table of Java special symbols
	// Each special symbol's text is the key to its Pascal token type
    public static final Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS = new Hashtable<>();
    static 
	{
        JavaTokenType values[] = JavaTokenType.values();
        for (int idx = FIRST_SPECIAL_INDEX; idx <= LAST_SPECIAL_INDEX; ++idx) 
		{
            SPECIAL_SYMBOLS.put(values[idx].getText(), values[idx]);
        }
    }
}
