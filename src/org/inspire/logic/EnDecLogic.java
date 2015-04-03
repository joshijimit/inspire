package org.inspire.logic;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

public class EnDecLogic {

     private  String algorithm = "DESede";
     private  Key key = null;
     private  Cipher cipher = null;

     public void setUp() throws Exception {
         key = KeyGenerator.getInstance(algorithm).generateKey();
         cipher = Cipher.getInstance(algorithm);
     }

     /*public static void main(String[] args) 
        throws Exception {
         setUp();
         
         byte[] encryptionBytes = null;
         String input = "I love you world";
         System.out.println("Entered: " + input);
         encryptionBytes = encrypt(input);
         System.out.println(encryptionBytes);
         System.out.println(
           "Recovered: " + decrypt(encryptionBytes));
     }*/

     public  byte[] encrypt(String input)
         throws InvalidKeyException, 
                BadPaddingException,
                IllegalBlockSizeException {
         cipher.init(Cipher.ENCRYPT_MODE, key);
         byte[] inputBytes = input.getBytes();
         return cipher.doFinal(inputBytes);
     }

     public  String decrypt(byte[] encryptionBytes)
         throws InvalidKeyException, 
                BadPaddingException,
                IllegalBlockSizeException {
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte[] recoveredBytes = 
           cipher.doFinal(encryptionBytes);
         String recovered = 
           new String(recoveredBytes);
         return recovered;
       }
}
