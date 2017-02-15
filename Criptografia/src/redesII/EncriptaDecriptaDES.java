package redesII;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
 
public class EncriptaDecriptaDES
{
 
 
       public static void main(String[] args) {
 
             try{
 
                 KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                 SecretKey chaveDES = keygenerator.generateKey();
 
                 Cipher cifraDES;
 
                 // Cria a cifra 
                 cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");
 
                 // Inicializa a cifra para o processo de encripta��o
                 cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);
 
                 // Texto puro
                 byte[] textoPuro = "Alan".getBytes();
 
                 System.out.println("Texto Puro : " + new String(textoPuro));
                 
                 System.out.print("Texto Puro em Bytes (int) : ");
                 
                 for(byte parteByte : textoPuro)
                	 System.out.print(parteByte);
                 
                 System.out.println();
 
                 // Texto encriptado
                 byte[] textoEncriptado = cifraDES.doFinal(textoPuro);
 
                 System.out.print("Texto Encriptado em Bytes (int) : ");
                 
                 for(byte parteByte : textoEncriptado)
                	 System.out.print(parteByte);
                 
                 System.out.println();
 
                 // Inicializa a cifra tamb�m para o processo de decripta��o
                 cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);
 
                 // Decriptografa o texto
                 byte[] textoDecriptografado = cifraDES.doFinal(textoEncriptado);
 
                 System.out.println("Texto Decriptografado : " + new String(textoDecriptografado));
 
             }catch(NoSuchAlgorithmException e){
                    e.printStackTrace();
             }catch(NoSuchPaddingException e){
                    e.printStackTrace();
             }catch(InvalidKeyException e){
                    e.printStackTrace();
             }catch(IllegalBlockSizeException e){
                    e.printStackTrace();
             }catch(BadPaddingException e){
                    e.printStackTrace();
             } 
 
       }
       
}