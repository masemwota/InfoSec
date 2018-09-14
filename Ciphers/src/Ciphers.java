public class Ciphers {
	
	public static void main(String [] args)
	{
		System.out.println("This is a crypto app");
		//System.out.println("List of ciphers: \n Simple \n Basic \n Affine");
		
		String sample = "Hello World";
		caeser(sample);
		
		sample = "Marietta"; 
		caeser(sample);
	}
	
	/**
	 * A simple substitution cipher
	 * @param input - the string to encrypt
	 * @param n - the number to translate by 
	 */
	public static void simple(String input, int n)
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
	}
	
	/**
	 * Caeser's cipher is one of the oldest in the world
	 * It is a simple substitution with a shift by 3
	 * @param input
	 */
	public static void caeser(String input)
	{
//		System.out.println(input);
//		input = input.toUpperCase();
//		
//		String output = "";
//		for(int i = 0; i < input.length(); i++) 
//		{
//			char c = input.charAt(i);
//			int num = getNum(c);
//			output += getChar(num + 3);
//		}
//		
//		System.out.println(output + "\n");
		simple(input, 3);
	}
	
	public static int getNum(char c) {
		return c - 'A';
    }
	
	public static char getChar(int num) {
		return (char) (num + 'A'); 
	}

}