
public class AFiveOneTester {
	public static void main (String [] args)
	{
		//initialize a 64 bit array which is the key 
		long key = 0b1010101000101010101110011001110110011000111100001110000011110001L; 
		
		//call the AFiveOne program with the key and the size of the plaintext
		//the size of the plaintext will be the size of the keystream
		AFiveOne program = new AFiveOne(key, 128); //example size of 128
		
		System.out.println(" ");
		int [] ks = program.getKeystream();
		
		for(int num : ks)
		 {
			 System.out.print(num);
		 }
	}
}
