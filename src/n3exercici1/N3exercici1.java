package n3exercici1;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class N3exercici1 {

    public static void main(String[] args) {

        start();
    }

    public static void start(){

        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("config.properties"));

            // Get only files from folder specify at config.properties
            Files.list(Paths.get((String) prop.get("PATH_TO_SCAN"))).filter(Files::isRegularFile)
                    .forEach(
                    x -> {
                        try {
                            AESUtil.givenFile_Encrypt_And_Decrypt(
                                    String.valueOf(x), // Path of file
                                    String.valueOf(prop.get("PATH_CRYPT")), //Folder destination
                                    stripExtension(String.valueOf( x.getFileName() )) //File name without extension
                            );
                        } catch (NoSuchAlgorithmException |
                                InvalidAlgorithmParameterException |
                                NoSuchPaddingException |
                                BadPaddingException |
                                InvalidKeyException |
                                IllegalBlockSizeException |
                                IOException e) {
                            e.printStackTrace();
                        }
                    }
            );


        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada a escanear!");

        } catch (FileNotFoundException e) {

            System.out.println("¡No existe el fichero indicado!");

        } catch (IOException e) {

            e.printStackTrace();
        }

    } // end Mmain

    static String stripExtension(String str) {

        if (str == null) return null;
        // Get position of last '.'.
        int pos = str.lastIndexOf(".");
        // If there wasn't any '.' just return the string as is.
        if (pos == -1) return str;
        // Otherwise, return the string, up to the dot.
        return str.substring(0, pos);
    }

}

