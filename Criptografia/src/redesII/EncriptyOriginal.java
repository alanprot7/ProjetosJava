package redesII;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncriptyOriginal {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {



		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		SecretKey desKey = keygen.generateKey();

		Cipher desCipher;

		// Create the cipher 
		desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		// Initialize the cipher for encryption
		desCipher.init(Cipher.ENCRYPT_MODE, desKey);


		// Our cleartext
		byte[] cleartext = "Joilson".getBytes();

		System.out.println("Texto Puro: "+ new String(cleartext));


		// Encrypt the cleartext
		byte[] ciphertext = desCipher.doFinal(cleartext);

		System.out.print("Texto Encriptado: ");
		
		for(byte parteByte : ciphertext){
			System.out.print(parteByte);
		}
		
		System.out.println();

		// Initialize the same cipher for decryption
		desCipher.init(Cipher.DECRYPT_MODE, desKey);

		// Decrypt the ciphertext
		byte[] cleartext1 = desCipher.doFinal(ciphertext);

		System.out.println("Texto Descriptografado: "+new String(cleartext1));

	}

}
