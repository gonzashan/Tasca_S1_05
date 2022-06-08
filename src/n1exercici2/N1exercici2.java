package n1exercici2;

import n1exercici1.N1exercici1;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class N1exercici2 {

    //private static final String FOLDER_TO_ACTION = System.getProperty("java.class.path");
    private static String FOLDER_TO_ACTION =  System.getProperty("java.class.path");

    private static final SimpleDateFormat DFI = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static ArrayList<String> stringArrayList = new ArrayList<>();

    private static int counterDirectories = 0, counterFiles = 0, writeToFile = 0;

    public static void setFOLDER_TO_ACTION(String pathOfAction) {

        FOLDER_TO_ACTION = pathOfAction;
    }

    public static void main(String[] args) {

        if(args.length > 0){
            System.out.println("tenemos argumentos: " + args[0]);
            setFOLDER_TO_ACTION(args[0]);
            start(0);

        } else {

            start(0);
        }

    }

    public static void start(int x) {
        try {
            if (x == 1) {
                writeToFile = 1;
            }
            setStringsInArray("\nListado de directorios y archivos en:\n");
            setStringsInArray(FOLDER_TO_ACTION + "\n");
            displayDirRecursively(FOLDER_TO_ACTION, 0, "\n");
            setStringsInArray("\n\nElementos recopilados:\n"
                    + "Carpetas: " + counterDirectories + "\nFicheros: " + counterFiles);


        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada!");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<String> getArrayListResults() {

        return stringArrayList;
    }


    public static void displayDirRecursively(String directory, int depth, String tab) {

        File file = new File(directory);

        if (depth > 0) {
            for (int i = 0; i < depth; i++) {
                System.out.print("   ");
            }
            if (writeToFile == 0){

                System.out.print("|__");  //└──
            }
        }

        printDirectoriesAndFiles(file, tab);

        if (file.isDirectory()) {

            List<File> elementsFolder = getContentFolder(file);

            counterDirectories++;
            N1exercici1.sortFilesList(elementsFolder);

            for (File value : elementsFolder) {
                String levelTabs = tab + "\t";
                displayDirRecursively(value.getPath(), depth + 1, levelTabs);
            }
        } else {

            counterFiles++;
        }
    }

    private static List<File> getContentFolder(File file) {

        return new ArrayList<>
                (List.of(Objects.requireNonNull(file.listFiles((new FilenameFilter() {

                    // Remove .DS_Store element from list.
                    @Override
                    public boolean accept(File dir, String name) {
                        return !name.equals(".DS_Store");
                    }
                })))));
    }


    private static void printDirectoriesAndFiles(File item, String tab) {

        if (writeToFile == 0) {
            System.out.printf(paintAsColor(item)
                            + "%s "
                            + "\033[0m"
                            + tab + "[" + paintAsColor(item) + "%s" + "\033[0m" + "] - %5s \n"
                    , item.getName()
                    , item.isDirectory() ? "Folder" : "File"
                    , DFI.format(item.lastModified())
            );
        }
        setStringsInArray(String.format(
                tab + " |__ %s %s [%s] - %5s"
                , item.getName()
                , tab
                , item.isDirectory() ? "Folder" : "File"
                , DFI.format(item.lastModified())));
    }

    public static void setStringsInArray(String results) {

        stringArrayList.add(results);
    }

    // Use it to paint folders and files
    private static String paintAsColor(File item) {
        return item.isDirectory() ? "\033[0;36m" : "\033[0;34m";
    }


}
