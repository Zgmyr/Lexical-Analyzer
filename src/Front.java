/*  Zachary Gmyr
 *  Project #1 for CS4250
 *  Front.java - a lexical analyzer system for simple arithmetic expression, translated from
 *      a given example program 'front.c'
 */

import java.io.FileReader;
import java.io.IOException;

public class Front {

    /* Global declarations */
    /* Variables */
    static int charClass;
    static StringBuilder lexeme = new StringBuilder(); // imposed 100 char limit
    static char nextChar;
    // static int lexLen; // (unnecessary: we use StringBuilder which has a length() method)
    static int nextToken;
    static FileReader in_fp;



    /* Character Classes */
    /* ORIGINAL: #define [token] [int value] */
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;
    static final int EOF = -1;


    /* Token Codes */
    /* ORIGINAL: #define [token] [int value] */
    static final int INT_LIT = 10;
    static final int IDENT = 11;
    static final int ASSIGN_OP = 20;
    static final int ADD_OP = 21;
    static final int SUB_OP = 22;
    static final int MULT_OP = 23;
    static final int DIV_OP = 24;
    static final int LEFT_PAREN = 25;
    static final int RIGHT_PAREN = 26;
    static final int SEMICOLON = 27; /* new token */


    /* main driver */
    public static void main(String[] args) {

        /* open the input data file */
        try {
            // ORIGINAL: if ((in_fp = fopen("front.in","r")) == NULL)
            in_fp = new FileReader("data_file.txt");

            getChar();
            do {
                lex();
            } while (nextToken != EOF);

            in_fp.close();
        }
        catch (Exception e){
            System.out.println("Error - Cannot open 'data_file.txt'. Ensure file exists in current working directory.");
            System.out.println(e);
        }

        System.out.println("END OF PROGRAM"); // DEBUG
    }

    /* lookup - a function to look up operators and parentheses and return the token */
    public static int lookup(char ch) {
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;

            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;

            case '+':
                addChar();
                nextToken = ADD_OP;
                break;

            case '-':
                addChar();
                nextToken = SUB_OP;
                break;

            case '*':
                addChar();
                nextToken = MULT_OP;
                break;

            case '/':
                addChar();
                nextToken = DIV_OP;
                break;

            /* new case - not in ORIGINAL Front.C */
            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                break;

            /* new case - not in ORIGINAL Front.C */
            case ';':
                addChar();
                nextToken = SEMICOLON;
                break;

            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;
    }

    /* addChar - a function to add nextChar to lexeme */
    /* imposes 100 character limit on lexeme */
    public static void addChar() {
        // ORIGINAL: if (lexLen <= 99) {
        if (lexeme.length() <= 99) {
            /* ORIGINAL: lexeme[lexLen++] = nextChar;
            ORIGINAL: lexeme[lexLen] = 0; */
            lexeme.append(nextChar);
        }
        else
            System.out.println("Error - lexeme is too long");
    }

    /* getChar - a function to get the next character of input and determine its character class */
    /* nextChar holds current character & charClass will be updated to LETTER, DIGIT, UNKNOWN, or EOF */
    public static void getChar() {
        try {
            int readChar; // temporarily holds FileReader value to determine charClass

            // ORIGINAL: if ((nextChar = getc(in_fp)) = EOF) {
            if ((readChar = in_fp.read()) != EOF) {
                nextChar = (char)readChar; // update static char variable
                // ORIGINAL: if (isalpha(nextChar))
                if (Character.isLetter(readChar))
                    charClass = LETTER;
                // ORIGINAL: else if (isdigit(nextChar))
                else if (Character.isDigit(readChar))
                    charClass = DIGIT;
                else
                    charClass = UNKNOWN;
            }
            else
                charClass = EOF;
        }
        catch (IOException e) {
            e.printStackTrace();
            charClass = EOF;
        }

    }

    /* getNonBlank - a function to call getChar until it returns a non-whitespace character */
    public static void getNonBlank() {
        // ORIGINAL: while (isspace(nextChar))
        while (Character.isWhitespace(nextChar))
            getChar();
    }

    /* lex - a simple lexical analyzer for arithmetic expressions */
    /* parses the current lexeme & updates nextToken */
    public static int lex() {
        // ORIGINAL: lexLen = 0;
        lexeme.setLength(0);
        getNonBlank();
        switch (charClass) {

            /* parse identifiers */
            case LETTER:
                addChar(); /* takes current first character & adds it to lexeme */
                getChar(); /* grabs next char updating nextChar & charClass */
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                } /* loop terminates upon whitespace / end of lexeme */
                nextToken = IDENT;
                break;

            /* parse integer literals */
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;

            /* Parentheses and operators */
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;

            /* EOF */
            case EOF:
                nextToken = EOF;
                /* ORIGINAL:
                * lexeme[0] = 'E';
                * lexeme[1] = 'O';
                * lexeme[2] = 'F';
                * lexeme[3] = '0'; */
                lexeme.replace(0,lexeme.length(),"EOF");
                break;
        } /* End of switch */

        // ORIGINAL: printf("The next token is: %d, Next Lexeme is %s\n", nextToken, lexeme);
        System.out.println("The next token is: " + nextToken + ", Next lexeme is " + lexeme);

        return nextToken;
    } /* End of function lex */

}
