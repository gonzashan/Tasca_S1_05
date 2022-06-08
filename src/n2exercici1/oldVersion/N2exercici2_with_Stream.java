package n2exercici1.oldVersion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.stream.Stream;

// Class to use Stream<Path> to walk along the directories
// cannot take File class to get file.lastModified()

public class N2exercici2_with_Stream {

    private static final String FOLDER_TO_ACTION = System.getProperty("java.class.path");
    private static Collator avoidAccents = Collator.getInstance(new Locale("es"));
    private static String auxText = "";

    public static void main(String[] args) {

        avoidAccents.setStrength(Collator.PRIMARY);

        Properties prop = new Properties();
        try {
            // Managing files
            prop.load(new FileInputStream(new File("config.properties")));
            FileWriter myWriter = new FileWriter(prop.get("PATH_FILE_TXT")+"filename.txt");



            Stream<Path> miStream = Files.walk(Paths.get((String) prop.get("PATH_TO_SCAN")))
                    .filter( file -> !file.getFileName().toString().equals(".DS_Store")) // Check you have only file names
                    .sorted((Comparator<Path>) (o1, o2) -> 0);

            miStream.forEach(new Consumer<Path>() {
                                 @Override
                                 public void accept(Path x) {

                                     if(x.getFileName().toString().contains(".")){
                                         auxText = x + " [Fichero] \n";
                                         System.out.print(auxText);
                                     } else {
                                         auxText = x + " [Carpeta] \n";
                                         System.out.print(auxText);
                                     }
                                     try {

                                         myWriter.write(auxText);
                                     } catch (IOException e) {

                                         e.printStackTrace();
                                     }
                                 }
                             }
            );

            myWriter.write("\nFiles in this folder.");
            myWriter.close();

        } catch (FileNotFoundException e) {

            System.out.println("¡No existe el fichero indicado!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}