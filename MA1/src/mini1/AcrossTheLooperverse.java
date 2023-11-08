package mini1;

import java.util.Scanner;

/**
* Some loop practice problems!
*/
public class AcrossTheLooperverse
{
/**
* Private constructor disables instantiation.
*/
private AcrossTheLooperverse()
{
}
/**
* Counts the number of positions in a pair of strings that have matching
characters.
* The strings need not be the same length.
* For example, countMatches("abcde", "xbydzzzzz") returns 2.
*
* @param s
* any string
* @param t
* any string
* @return
* number of positions in which the characters match in the two strings
*/
public static int countMatches(String s, String t)
{
	int count = 0;
    int minLength = Math.min(s.length(), t.length());

    for (int i = 0; i < minLength; i++) {
        if (s.charAt(i) == t.charAt(i)) {
            count++;
        }
    }

    return count;
}
/**
* Determines the number of iterations of Newton's method
* required to approximate the square root of x within
* the given bound. Newton's method starts out by
* setting the initial approximate <em>answer</em> to x.
* Then in each iteration, <em>answer</em> is replaced by
* the quantity <em>(answer + x / answer) / 2.0</em>.
* The process stops when the difference between
* x and <em>(answer * answer)</em> is strictly less than
* the given bound <code>err</code>. The method
* returns the number of iterations required.
* The given value x must be non-negative.
* <p>
* For example, given x = 10 the first three iterations
* of Newton's method produce the approximate values
* 5.5, 3.66, and 3.20. Those three values squared are
* 30.29, 13.39, and 10.21, respectively.
* Therefore <code>countSteps(10, 1.0)</code>
* returns 3, since it takes 3 iterations to get the result 10.21
* that is within 1.0 of x.
* On the other hand, <code>countSteps(10, 200)</code> returns 0,
* since 10 * 10 = 100 is already within 200 units of x = 10.
* @param x
* value whose square root is to be approximated
* @param err
* given bound for the approximation
* @return
* number of iterations required to get an approximation
* whose square is within the given bound of x
*/
public static int countSteps(double x, double err)
{
	if (x < 0 || err < 0) {
        throw new IllegalArgumentException("Both x and err must be non-negative.");
    }

    double answer = x; // Initial approximation
    int iterations = 0;

    while (Math.abs(x - (answer * answer)) >= err) {
        answer = 0.5 * (answer + x / answer);
        iterations++;
    }

    return iterations;
}
/**
* Returns the second largest value in a list of numbers,
* where the list is given as a string of text containing integer
* values separated by arbitrary whitespace. Duplicates are allowed, so
* the largest and second largest numbers could be the same; for example,
* given the string "17 137 42 137", the method returns 137.
* The behavior is undefined if the provided string contains any
* non-numeric values or contains fewer than two numbers.
* @param text
* string of text containing at least two numbers separated by whitespace
* @return
* second largest value in the string
*/
public static int findNextToLargest(String text)
{
	Scanner scanner = new Scanner(text);
    int largest = scanner.nextInt();
    int secondLargest = scanner.nextInt();
    
    if(secondLargest >= largest) {
    	int num = secondLargest;
    	secondLargest = largest;
    	largest = num;
    	}
    
    while (scanner.hasNext()) {
    	int num = scanner.nextInt();
    	if(num<largest && num >= secondLargest) {
    		secondLargest = num;
    	}
    	else if(num> largest) {
    		largest = num;
    	}
    
       
    }
    scanner.close();
    return secondLargest;
    
}

/**
* Given a string, returns a new string in which text between
* the character '(' and the next ')' is converted to star '*' characters.
* Any '(' characters encountered while converting to stars
* are ignored, and likewise, any ')' characters encountered
* without a corresponding preceding '(' are also ignored.
* If a '(' is encountered that would ordinarily not be ignored, but
* there is no matching ')' anywhere in the rest of the string, that
* '(' is ignored.
* <p>
* For example,
* <ul>
* <li> given string <pre>"aa(rdv)ark"</pre> returns <pre>"aa***ark"</pre>
* <li> given string <pre>"aa(r((((dv)a()))()(r)(k"</pre> returns
<pre>"aa***a*k"</pre>
* </ul>
* @param s
* any string
* @return
* new string in which text between matching '(' and ')' characters
* has been converted to star characters
*/
public static String starMaker(String s) {
	StringBuilder result = new StringBuilder();
    boolean insideParentheses = false;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (c == '(') {
            insideParentheses = true;
        } else if (c == ')') {
            insideParentheses = false;
        } else if (insideParentheses) {
        	result.append('*');
        } else {
            result.append(c);
        }
    }


       return result.toString();
}	



/**
* Given a string s, appends the smallest number of characters
* (possibly none) that will make s into a palindrome, and returns the result.
* Examples:
* <ul>
* <li>Given "taco", returns "tacocat"
* <li>Given "foo", returns "foof"
* <li>Given "hanna", returns "hannah"
* <li>Given "abcba", returns "abcba"
* </ul>
* @param s
* given string
* @return
* smallest palindrome constructed from s
*/
public static String makeSmallestPalindrome(String s)
{
	if (isPalindrome(s)) {
       return s; // If it's already a palindrome, no modification needed.
   }

	StringBuilder palindromeBuilder = new StringBuilder(s);

    int i = 0;
    int j = s.length() - 1;

    while (i < j) {
        if (s.charAt(i) == s.charAt(j)) {
            // Characters match, continue checking the rest
            i++;
            j--;
        } else {
            // Characters don't match, append the character from the end to make a palindrome
            palindromeBuilder.insert(j + 1, s.charAt(i));
            i++;
        }
    }

    return palindromeBuilder.toString();
}
private static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }

    return true;
}
/**
* Given a size n >= 1, prints a diagram to standard output,
* in the pattern shown below for n = 5.
* <pre>
a
a b a
a b c b a
a b c d c b a
a b c d e d c b a
*
* </pre>
* Note that there is exactly one space between letters and at the widest part
* of the diagram the line should have no leading spaces. It is ok if the lines
* have an extra space at the end. Note you can easily convert a number
* i = 0, 1, 2, ... to a character ch = 'a', 'b', 'c', ... with the
* code
* <code>ch = (char) ('a' + i);</code>
*
* @param n
* number of rows in the diagram
*/
public static void makePattern(int n)
{
    int maxSpaces = 2 * n - 2;

    for (int i = 0; i < n; i++) {
        // Print leading spaces
        for (int j = 0; j < maxSpaces - i*2; j++) {
            System.out.print(" ");
        }

        // Print characters in ascending order
        for (int j = 0; j <= i; j++) {
            char ch = (char) ('a' + j);
            System.out.print(ch);
            if (j < i) {
                System.out.print(" "); // Add a space between characters
            }
        }
        for (int j = i - 1; j >= 0; j--) {
            char ch = (char) ('a' + j);
            System.out.print(" " + ch); // Add a space before characters
        }

        

        // Move to the next line
        System.out.println();
    }
	    
}
/**
* Checks a guess for a secret word and returns a feedback string.
* This algorithm is inspired by, but not the same as, that used
* by the game Wordle.
* The returned string is the same length as the guess, and the
* character at index i is filled in as follows, where
* g_i, s_i, and r_i denote the character at position i in
* the guess, the secret word, and the result string, respectively.
*
* <ul>
* <li>if g_i matches s_i, then r_i is '*'
* <li>if g_i does not occur in the secret word at all,
* then r_i is '-'
* <li>if g_i does not match s_i, but the secret word does
* have an unmatched occurrence of g_i, then r_i is '?'.
* (More precisely, an "unmatched occurrence" means that there is some
* index j such that g_i is equal to s_j but g_j is not equal
* to s_j.)
* </ul>
*
* For example,
* <pre>
* Guess: "guess"
* Secret: "geese"
* Result: "*-**-"
*
* Guess: "bobbly"
* Secret: "blobby"
* Result: "*??*?*"
*
* Guess: "aabbbb"
* Secret: "abbcde"
* Result: "*-*???"
* </pre>
* (Aside to Wordle fans: note that the latter case differs from
* the algorithm actually
* used by Wordle, which would return "*-*?--", because it would
* count the number of unmatched b's in the secret word and note
* that there is only one, so only the first incorrect b in the guess
* is labeled with a question mark. In this method we are ignoring
* that subtlety.)
* <p>
* The method returns null if the two given strings are not the same length.
*
*
* @param guess
* the guessed word
* @param secret
* the secret word
* @return
* result string with feedback for the guess
*/
public static String wordGameChecker(String guess, String secret)
{
	 if (guess.length() != secret.length()) {
	        return null;
	    }

	    StringBuilder result = new StringBuilder();
	    
	    for (int i = 0; i < guess.length(); i++) {
	        char guessChar = guess.charAt(i);
	        char secretChar = secret.charAt(i);

	        if (guessChar == secretChar) {
	            result.append('*'); // Matched character
	        } else {
	            boolean found = false;
	            for (int j = 0; j < secret.length(); j++) {
	                if (guessChar == secret.charAt(j) && guess.charAt(j) != secret.charAt(j)) {
	                    found = true;
	                    break;
	                }
	            }
	            result.append(found ? '?' : '-');
	        }
	    }

	    return result.toString();
}
}
