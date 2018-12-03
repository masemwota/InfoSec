import java.math.BigInteger;

public class AFiveOne {

	private long key; 
	private int keystreamSize; 
	
	//the shift registers 
	int xRegister[]; 
	int yRegister[]; 
	int zRegister[]; 
	
	/**
	 * The constructor for the AFiveOne program 
	 * @param key - the 64-bit input key 
	 * @param size - the desired size of the keystream
	 */
	public AFiveOne(long key, int size) 
	{
		this.key = key; 
		keystreamSize = size; 
		
		xRegister = new int[19]; 
		yRegister = new int[22]; 
		zRegister = new int[23]; 
		initialFill(); 
	}
	
	/**
	 * Does the initial fill of the registers
	 */
	public void initialFill() 
	{
		String keyString = Long.toBinaryString((long)key);
		System.out.println(keyString);
		
		int xCount = 0; 
		int yCount = 0; 
		int zCount = 0;

	    for (int i = 0; i < keyString.length(); i++) 
	    {
	    		int current = (keyString.charAt(i) - '0');
	    		
	    		//place first 19 into x
	    		if(i < 19) {
	    			xRegister[xCount] = current;
	    			xCount++; 
	    		}
	    		
	    		//place middle 22 bits into y
	    		else if(i < 41) {
	    			yRegister[yCount] = current; 
	    			yCount++; 
	    		}
	    		
	    		//place last 23 bits into Z 
	    		else {
	    			zRegister[zCount] = current; 
	    			zCount++; 
	    		}
	    }
	    
	    printRegisters();
	}
	
	
	/**
	 * Print the current value of the registers
	 */
	 public void printRegisters()
	 {
		 //X
		 for(int num : xRegister)
		 {
			 System.out.print(num);
		 }
		 System.out.println(" ");
		
		 //Y
		 for(int num : yRegister)
		 {
			 System.out.print(num);
		 }
		 System.out.println(" ");
		 
		 //Z 
		 for(int num : zRegister)
		 {
			 System.out.print(num);
		 }
	 }
	 
	 /**
	  * Get the majority of three numbers 
	  */
	 public int maj(int xValue, int yValue, int zValue)
	 {
		 //8 possibilities 
		 if(xValue == 0 && yValue == 0 && zValue == 0)
			 return 0; 
		 else if(xValue == 0 && yValue == 0 && zValue == 1)
			 return 0; 
		 else if(xValue == 0 && yValue == 1 && zValue == 0)
			 return 0; 
		 else if(xValue == 0 && yValue == 1 && zValue == 1)
			 return 1; 
		 else if(xValue == 1 && yValue == 0 && zValue == 0)
			 return 0; 
		 else if(xValue == 1 && yValue == 0 && zValue == 1)
			 return 1; 
		 else if(xValue == 1 && yValue == 1 && zValue == 0)
			 return 1; 
		 else if(xValue == 1 && yValue == 1 && zValue == 1)
			 return 1; 
		 else 
			 return 0; 
	 }
	 
	 
	 /**
	  * Get and set the new first bits for the registers that need to be shifted
	  * @param majority - needed for comparison
	  */
	 public void newFirstBits(int majority)
	 {
		 //from the majority winner, get the registers that need to be shifted
		 
		 if(xRegister[8] == majority)  //X steps
		 {
			 int newValue = xRegister[13] ^ xRegister[16] ^ xRegister[17] ^ xRegister[18];
			 
			 //shift X to the right 
			 for( int index = xRegister.length-2; index >= 0 ; index--) {
		            xRegister[index+1] = xRegister[index];
		     }
			 
			 xRegister[0] = newValue; 
			 //System.out.println("new for X: " + newValue);
		 }
		 
		 if(yRegister[10] == majority) //Y steps
		 {
			 //t = y20y21
			 int newValue = yRegister[20] ^ yRegister[21];
			 
			 //shift Y to the right 
			 for( int index = yRegister.length-2; index >= 0 ; index--) {
		            yRegister[index+1] = yRegister[index];
		     }
			 
			 yRegister[0] = newValue; 
			 //System.out.println("new for Y: " + newValue);
		 } 
		 
		 if(zRegister[10] == majority)  //Z steps
		 {
			 //t = z7z20z21z22
			 int newValue = zRegister[7] ^ zRegister[20] ^ zRegister[21] ^ zRegister[22];
			 
			 //shift Z to the right 
			 for( int index = zRegister.length-2; index >= 0 ; index--) {
		            zRegister[index+1] = zRegister[index];
		     }
			 
			 zRegister[0] = newValue; 
			 //System.out.println("new for Z: " + newValue);
		 }
	 }
	 
	 public int getKeystreamBit()
	 {
		 //majority vote 
		 int m = maj(xRegister[8], yRegister[10], zRegister[10]); 
		 
		 //compute new first bits and shift 
		 newFirstBits(m); 
		 
		 //compute new bit of keystream -- x18y21z22
		 int kBit = xRegister[18] ^ yRegister[21] ^ zRegister[22]; 
		 
		 return kBit; 
	 }
	 
	 public int[] getKeystream() 
	 {
		 int [] keystream = new int[keystreamSize]; 
		 
		 for(int i = 0; i < keystreamSize; i++) 
		 {
			 //get a keystream bit to fill the array as big as the size
			 keystream[i] = getKeystreamBit(); 
		 }
		 
		 return keystream;
	 }
	   
}
