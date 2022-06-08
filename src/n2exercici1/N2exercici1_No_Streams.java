package n2exercici1;

import n1exercici3.N1exercici3;

import java.io.*;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class N2exercici1_No_Streams {

    private static final SimpleDateFormat DFI = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static String auxText;
    private static String nameFileTxt;
    private static int counterDirectories = 0, counterFiles = 0;


    public static void main(String[] args) {

        Properties prop = new Properties();

        try {
            // Load file with properties
            prop.load(new FileInputStream(new File("config.properties")));
           // System.out.println(new File("config.properties"));
            nameFileTxt = prop.get("PATH_FILE_TXT") + prop.getProperty("NAME_FILE");
            // Create and delete to erase contents into the file(El Yin y el Yan de todo).
            FileWriter onlyForErease = new FileWriter(nameFileTxt);
            File carpeta = new File((String) prop.get("PATH_TO_SCAN"));
            // File to write the treemap folders
            FileWriter myWriter = new FileWriter((String) prop.getProperty("NAME_FILE"));
            writingInFile("\n** Listado de directorios y archivos ***\n");
            writingInFile("----------------------------------------------------" +
                    "-------------------------\n");
            // Reading the root
            File[] listFiles = carpeta.listFiles();

            for (File file : listFiles) {

                printResults(file, "\t");
            }

            writingInFile("\nCarpetas:  " + counterDirectories + "\nFicheros: " + counterFiles);

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada a escanear!");

        } catch (FileNotFoundException e) {

            System.out.println("¡No existe el fichero indicado!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void printResults(File file, String tab) {

        if (file.isDirectory()) {

            counterDirectories++;
            auxText = String.format(
                    "%s%-30s %-10s %s\n "
                    , tab, file.getName()
                    , "[Folder] "
                    , DFI.format(file.lastModified()));
            System.out.print(auxText);
            writingInFile(auxText);

            for (File ficheroHijo : getFilesSortedWithAccents(file)) {

                String factor = tab + "\t" + "|";
                printResults(ficheroHijo, factor);
            }

        } else {

            counterFiles++;
            if (!file.getName().contains(".DS_Store")) {
                auxText = String.format(
                        "%s%-30s %-10s %s\n "
                        , tab, file.getName()
                        , "[Item] "
                        , DFI.format(file.lastModified()));

                System.out.print(auxText);
                writingInFile(auxText);
            }
        }
    }

    private static List<File> getFilesSortedWithAccents(File file) {

        // Avoiding accents into the directory in filesInFolder names.
        Collator avoidAccents = Collator.getInstance(new Locale("es"));
        avoidAccents.setStrength(Collator.PRIMARY);

        List<File> filesInFolder = new ArrayList<File>(List.of(file.listFiles()));

        filesInFolder.sort(new Comparator<>() {
            @Override
            public int compare(File o1, File o2) {

                return avoidAccents.compare(o1.getName(), o2.getName());
            }
        });

        return filesInFolder;
    }

    private static void writingInFile(String auxText) { // write into the file

        BufferedWriter grabb;

        N1exercici3.controlFile(nameFileTxt, auxText);

    }


}
























/* Files.walk(Paths.get("./src"))
                    .filter(Files::isRegularFile)
                    .filter( file -> !file.getFileName().toString().equalsx(".DS_Store")) // Check you have only file names
                    .map(Path::toString) // map to string
                    .sorted(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return avoidAccents.compare(o1,o2);
                        }

                    }) // sort
                    .collect(Collectors.toList())
                    .forEach(System.out::println); // create list


 Properties props = System.getProperties();
        for(Map.Entry<Object, Object> prop : props.entrySet())
            System.out.println("Property: +" + prop.getKey() + "\tValue: " + prop.getValue());

 */