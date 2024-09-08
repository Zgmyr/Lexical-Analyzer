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
    static StringBuilder lexeme = new StringBuilder(); // holds each lexeme with 100 char limit
    static char nextChar;
    // static int lexLen; // (unnecessary: we use StringBuilder which has a length() method)
    static int token;
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


    /* main driver */
    public static void main(String[] args) {

        /* open the input data file */
        try {
            // ORIGINAL: if ((in_fp = fopen("front.in","r")) == NULL)
            in_fp = new FileReader("data_file.txt");

            // TESTING getChar()
            getChar();
            System.out.println("nextChar = " + nextChar +
                    "\ncharClass = " + charClass);
            getChar();
            System.out.println("nextChar = " + nextChar +
                    "\ncharClass = " + charClass);

            /* process contents of the data file */
            // call upon getChar()
            // do lex() while nextToken != EOF

            in_fp.close();
        }
        catch (Exception e){
            System.out.println("Error - Cannot open 'data_file.txt'. Ensure file exists in current working directory.");
            System.out.println(e);
        }

        System.out.println("END OF PROGRAM"); // DEBUG
    }

    /* addChar - a function to add nextChar to lexeme */
    public static void addChar() {
        // ORIGINAL: if (lexLen <= 99) {
        if (lexeme.length() <= 99) {
            // ORIGINAL: lexeme[lexLen++] = nextChar;
            // ORIGINAL: lexeme[lexLen] = 0;
            lexeme.append(nextChar);
        }
        else
            System.out.println("Error - lexeme is too long");
    }

    /* getChar - a function to get the next character of input and determine its character class */
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
    /*public static int lex() {
        // ORIGINAL: lexLen = 0;
        lexeme.setLength(0);
        getNonBlank();
        // switch (charClass) ...
    }*/

}
