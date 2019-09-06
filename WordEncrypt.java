/*
 * Text : ATTACKATONCE
Shift: 4
Cipher: EXXEGOEXSRGI

A message is encrypted by replacing every character with the character which is n(like 3) positions to its right. Write the logic to decrypt the message. Assume that all 

the characters in the message are in lower case. [For Eg, a->d, b->e…..z->c]
 * 
 * 
 * */


public class WordEncrypt {
	
	public static void main(String[] args) 
    { 
        //String text = "ATTACKATONCE"; 
        String text = "naggaroz";
        int s = 4; 
        System.out.println("Text  : " + text); 
        System.out.println("Shift : " + s); 
        System.out.println("Cipher: " + encrypt(text, s)); 
    }

	private static String encrypt(String text, int s) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<text.length();i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				char ch = (char) (((int)text.charAt(i)+s-65)%26+65);
				sb.append(ch);				
			}
			else {
				char ch = (char)(((int)text.charAt(i)+s-97)%26+97);
				sb.append(ch);
			}
		}
		return sb.toString();
	} 

}
