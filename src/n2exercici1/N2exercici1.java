package n2exercici1;

import java.io.*;
import java.util.Properties;

public class N2exercici1 {

    private static String auxText;
    private static String nameFileTxt;


    public static void main(String[] args) {

        Properties prop = new Properties();

        try {
            // Load file with properties
            prop.load(new FileInputStream(new File("config.properties")));
            // System.out.println(new File("config.properties"));
            nameFileTxt = prop.get("PATH_FILE_TXT") + prop.getProperty("NAME_FILE");
            System.out.println(new File("config.properties").getAbsolutePath());
            File carpeta = new File((String) prop.get("PATH_TO_SCAN"));
            // File to write the treemap folders
            FileWriter myWriter = new FileWriter((String) prop.getProperty("NAME_FILE"));

            // Reading the root

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada a escanear!");

        } catch (FileNotFoundException e) {

            System.out.println("¡No existe el fichero indicado!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}