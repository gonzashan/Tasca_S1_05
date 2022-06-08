package n1exercici3;

import n1exercici2.N1exercici2;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class N1exercici3 {

    public static Properties prop = new Properties();
    private static String argsPath = System.getProperty("java.class.path");

    public static void main(String[] args) {

        if(args.length > 0){

            System.out.println("viene con argumentos: " + args[0]);
            argsPath = args[0];
            start();

        } else {

            start();
        }
    }

    public static void start() {

        try {

            String useForConsole = "";
            String nameFileTxt;
            // Substring "user.dir" to avoid /out/production/... from folder Project
            if (System.getProperty("user.dir").contains("out")) {

                useForConsole = System.getProperty("user.dir").split("out")[0];

            }

            prop.load(new FileInputStream(new File(useForConsole + "config.properties")));

            if (System.getProperty("user.dir").contains("out")) {

                nameFileTxt = useForConsole +
                        String.valueOf(prop.get("PATH_FILE_TXT")).replace(".","")
                        + prop.getProperty("NAME_FILE");

            } else {

                nameFileTxt = prop.getProperty("PATH_FILE_TXT") + prop.getProperty("NAME_FILE");
            }

            // Create and delete to erase contents into the file(El Yin y el Yan de todo).
            FileWriter onlyForErease = new FileWriter(nameFileTxt);
            // Engine process
            N1exercici2.setFOLDER_TO_ACTION(argsPath);
            N1exercici2.start(1);
            writingDataIntoFileTxt(N1exercici2.getArrayListResults(), nameFileTxt);

            // Final action
            System.out.print("\nLo encontrarás en: " + "\033[0;33m"
                    + nameFileTxt + "\033[0m\n");

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada!");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


    public static void writingDataIntoFileTxt(ArrayList<String> list, String file) {

        System.out.println();
        BufferedWriter grabb;

        for (String text : list) {

            controlFile(file, text);
        }

        System.out.println("Fichero de txt creado con éxito");

    }

    public static void controlFile(String file, String text) {
        BufferedWriter grabb;
        try {
            grabb = new BufferedWriter(new FileWriter(file, true));
            grabb.write(text);
            grabb.close();

        } catch (IOException e) {

            System.out.println("Errores de escritura");

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada!");

        }
    }

}




