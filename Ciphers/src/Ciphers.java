import java.util.*;

public class Ciphers {
	
	public static void main(String [] args)
	{
		System.out.println("This is a crypto app");
		//System.out.println("List of ciphers: \n Simple \n Basic \n");
		
		simpleKeySearch("FCGUNKBHEJGCLK");
		countCharacters("FCGUNKBHEJGCLK");
	}
	
	
	/**
	 * A method that tries all possible keys for simple substitution 
	 * @param input
	 */
	public static void simpleKeySearch(String input)
	{
		for(int i = 0; i < 26; i++)
		{
			simple(input, i);
		}
	}
	
	/**
	 * A simple substitution cipher
	 * @param input - the string to encrypt
	 * @param n - the number to shift by 
	 */
	public static String simple(String input, int n)
	{		
		System.out.println(input + "; n = " + n);
		input = input.toUpperCase();
		String output = "";
		
		for(int i = 0; i < input.length(); i++) 
		{
			char c = input.charAt(i);
			output += getChar(getNum(c) + n);
		}
		
		System.out.println(output + "\n");
		return output;
	}
	
	/**
	 * Given a ciphertext and an n, get back the plaintext that would have been the input 
	 * to a simple substitution cipher with the provided n
	 * @param input - the ciphertext
	 * @param n - the key
	 */
	public static void decryptSimple(String input, int n)
	{
		simple(input, 0 - n); 
	}
	
	/**
	 * Caeser's cipher is one of the oldest in the world
	 * It is a simple substitution with a shift by 3
	 * @param input
	 */
	public static void caeser(String input)
	{
		simple(input, 3);
	}
	
	//helper methods 
	public static int getNum(char c) {
		return c - 'A';
    }
	
	public static char getChar(int num) {
		return (char) ((num % 26) + 'A'); 
	}

	
	public static void countCharacters(String input)
	{
		Map<Character,Integer> frequencies = new HashMap<>();
		for (char ch : input.toCharArray()) 
		   frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);
		
		for (Character ch : frequencies.keySet())
			System.out.println(ch + " " + frequencies.get(ch));
        
	}
}
