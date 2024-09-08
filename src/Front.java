/*  Zachary Gmyr
 *   Project #1 for CS4250
 *   Front.java - a lexical analyzer system for simple arithmetic expression, translated from
 *       a given example program 'front.c'
 */

public class Front {


    /* Global declarations */
    /* Variables */
    static StringBuilder lexeme = new StringBuilder(); // holds each lexeme with 100 char limit
    char nextChar;

    public static void main(String[] args) {
        lexeme.append("Hello World");
        System.out.println(lexeme.toString());
    }



}
