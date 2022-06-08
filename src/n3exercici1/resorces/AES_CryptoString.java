package n3exercici1.resorces;

import n3exercici1.AESUtil;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class AES_CryptoString {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    //Define a method for generating the AES key from
    // a given password with 65,536 iterations and a key length of 256 bits
    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    /*
    *  +-++-++-++-++-++-++-+ +-+ +-++-++-++-++-++-++-+ +-++-++-+ +-++-++-++-++-++-++-+
        |V||e||r||s||i||o||n| 1  |E||n||c||r||y||p||t| |a||n||d| |D||e||c||r||y||p||t|
    +-++-++-++-++-++-++-+ +-+ +-++-++-++-++-++-++-+ +-++-++-+ +-++-++-++-++-++-++-+*/

    //Version 1 Encrypt for Strings
    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    // Version 1 Decrypt for Strings
    public static String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            //  System.out.println("Error while decrypting: " + e.toString());
            System.out.println("Error: Clave o texto no válido ") ;
        }
        return null;
    }


    /*   _    _    _    _    _    _    _     _
  / \  / \  / \  / \  / \  / \  / \   / \
 ( V )( e )( r )( s )( i )( o )( n ) ( 2 )
  \_/  \_/  \_/  \_/  \_/  \_/  \_/   \_/  */

    /*/*Version 2

    To implement input string encryption, we first need to generate the secret key and IV according to
    the previous section. As the next step, we create an instance from the Cipher class by using the
    getInstance() method.

     Additionally, we configure a cipher instance using the init() method with a secret key, IV,
     and encryption mode. Finally, we encryptString the input string by invoking the doFinal() method.
     This method gets bytes of input and returns ciphertext in bytes*/

    public static String encryptString(String algorithm, String input, SecretKey key,
                                       IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }


    //For decrypting an input string, initializing our cipher using the DECRYPT_MODE to decryptString the content:
    public static String decryptString(String algorithm, String cipherText, SecretKey key,
                                       IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }





    public static void main(String[] args) {

        final String secretoKey = "123456";

        try {

        System.out.println("\n------ AES ECB Password-based Decryption ------");
            System.out.printf("%-35s: %s","Llave generada en base (128)"
                    , "\033[0;32m" + generateKey(192) +"\033[0m\n"
            );

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        String originalString = "En un lugar de La Mancha ...";
        // System.out.println("Texto a encriptar: " + originalString);
        // encriptado con la clave '123456' "  m/FpqPVjNMdfPqx9QDSB92Qw0oH3/LhUfYp1VcfNxn0=  "

        //Encript
        String encryptedString = AES_CryptoString.encrypt(originalString, secretoKey);
        //System.out.println("\033[0;33m" + encryptedString + "\033[0m");

        desencripta2();
    }

    public static void desencripta2() {

        Scanner scanner = new Scanner(System.in);
        System.out.printf("%-35s: ","Pega la cadena a desencriptar");
        String encryptedString = scanner.nextLine();
        System.out.printf("%-35s: ","Ahora, introduce la clave");
        String decryptedString = AES_CryptoString.decrypt(encryptedString, scanner.nextLine());

        if (decryptedString != null) {

            System.out.printf("%-35s: %s\n","Texto desencriptado *********"
                    , "\033[0;32m" + decryptedString);
        }

    }

    public static void desencripta(String encryptedString) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la clave: ");
        String decryptedString = AES_CryptoString.decrypt(encryptedString, scanner.nextLine());

        if (decryptedString != null) {

            System.out.println("Lo secretos son: \n" + decryptedString);
        }
    }



    // With versions 2 of Encrypt and Decrypt
    public static void givenString_whenEncrypt_thenSuccessVersion2()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        String input = "itAcademy";
        SecretKey key = AESUtil.generateKey(128);
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = AES_CryptoString.encryptString(algorithm, input, key, ivParameterSpec);
        String plainText = AES_CryptoString.decryptString(algorithm, cipherText, key, ivParameterSpec);
        System.out.println("\n┌ @TEST ENCRIPTACIÓN Y DESENCRIPTACIÓN EN'String'\t──────────────┐");
        System.out.println("│Texto '" + input + "' encriptado: " + "\033[0;32m" + cipherText + "\033[0m");
        System.out.println("│Texto desencriptado: " + "\033[0;33m" + plainText);
        System.out.println("└─────────────────────────────────────────────────────────────────┘");

    }
}