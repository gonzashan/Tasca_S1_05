package n3exercici1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtil {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    // method for generating an IV
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    //method for generating the AES key with the size of n (128, 192, and 256) bits
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }



    /*Now ile using the AES algorithm. The steps are the same, but we need some
    IO classes to work with the files. Let's encryptString a text file:/*/

    public static void encryptAndDecryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
                                   File inputFile, File outputFile, int MODE_TODO) throws IOException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        // Cipher.ENCRYPT_MODE = 1 and Cipher.DECRYPT_MODE = 2
        // our int MODE value swap this modes
        cipher.init(MODE_TODO, key, iv);

        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();
    }



    public static void main(String[] args) throws Exception {

        N3exercici1.start();
    }


    public static void givenFile_Encrypt_And_Decrypt(String pathFileIn, String folderCrypt, String fileNameNoExtension)

            throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException {

        SecretKey key = AESUtil.generateKey(128);
        String miClave = "123456";
        String algorithm = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AESUtil.generateIv();

       // File inputFile = new File("./src/resources/readMe.txt");
        File inputFile = new File(pathFileIn);

        File encryptedFile = new File(folderCrypt + fileNameNoExtension + ".encrypted");
        File decryptedFile = new File(folderCrypt + fileNameNoExtension + ".decrypted");

        //javax.crypto.spec.SecretKeySpec@fffe93ed
        System.out.println("** Archivo  ***\n" + fileNameNoExtension + ".*  -> encriptado y desencriptado.");
        AESUtil.encryptAndDecryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile,1);
        AESUtil.encryptAndDecryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile,2);


    }



}