package n1exercici1;

import n1exercici2.N1exercici2;
import n1exercici3.N1exercici3;
import n1exercici4.N1exercici4;
import n1exercici5.N1exercici5;

import java.io.File;
import java.text.Collator;
import java.util.*;


public class N1exercici1 {

    // We aks on commands line about witch directory you want to scan. This is only for testing.
    public static String FOLDER_TO_ACTION =  System.getProperty("java.class.path");
    // N1exercici4
    private static final String GET_FILE_TXT = "./src/resources/tree_map_folders.txt";

    public static void main(String[] args) {

        try {

            File folder = new File(FOLDER_TO_ACTION); // folderToAction -- args[0] read from commands line

            List<File> files = new ArrayList<>(List.of(Objects.requireNonNull(folder.listFiles())));

            if (files.size() == 0) {

                System.out.println("No hay elementos dentro de la folder actual");

            } else {

                //N1exercici1
                printHeadSection("Tasca 5 - Exercici1");
                showingResults(sortFilesList(files));
                pressEnterToContinue();
                //N1exercici2 print on screen results
                printHeadSection("Tasca 5 - Exercici2 - print on screen results from walk along directories");
                pressEnterToContinue();
                N1exercici2.setFOLDER_TO_ACTION( System.getProperty("java.class.path"));
                N1exercici2.start(0);
                //N1exercici3  write to a file results   "./src/resources/tree_map_directories.txt"
                printHeadSection("Tasca 5 - Exercici3 - " +
                        "write results to file tree_map_folders.txt");
                pressEnterToContinue();
                N1exercici3.start();
                //N1exercici4 read file .txt
                printHeadSection("Tasca 5 - Exercici4 - read file .txt");
                pressEnterToContinue();
                N1exercici4.readTxtFile(GET_FILE_TXT);
                //N1exercici5 Serializar-Deserializar
                printHeadSection("Tasca 5 - Exercici5 -  Serializar-Des Serializar");
                pressEnterToContinue();
                N1exercici5.starts();

            }
        } catch (NullPointerException e){

            System.out.println("¡No existe el directorio o la ruta indicada!");

        } catch (Exception e){

            System.out.println(e.getMessage());
        }
    }


    public static List<File> sortFilesList(List<File> fileList) {

        // Avoiding accents into the directory on files names.
        Collator avoidAccent  = Collator.getInstance(new Locale("es"));
        avoidAccent.setStrength(Collator.PRIMARY);

        // Anonymous class
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return avoidAccent.compare(o1.getName(), o2.getName());
            }
        });
        // Lambda method way
       //  fileList.sort ((a, b) -> avoidAccent.compare(a.getName(), b.getName()));

        return fileList;
    }


    public static void showingResults(List<File> fileListSorted) {

        System.out.println("\nContenido directorio " + FOLDER_TO_ACTION);

        for (File file : fileListSorted) {
            System.out.printf((file.isDirectory() ? "\033[0;36m" : "\033[0;34m") + "%s"
                            + "\033[0m" + "\n",
                    file.getName()
            );
        }
    }

    public static void pressEnterToContinue() {
        System.out.print("\n\tPrem Enter per continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printHeadSection(String s) {
        String ANSI_YELLOW = "\033[0;33m";
        String ANSI_RESET = "\033[0m";
        System.out.println("\n"  + ANSI_YELLOW + printSymbol('─', 52) + ANSI_RESET);
        System.out.println( s + " *** " + ANSI_RESET + "");
        System.out.println( ANSI_YELLOW + printSymbol('─', 52) + ANSI_RESET);
    }
    public static String printSymbol(char e, int nTimes) {
        return String.valueOf(e).repeat(nTimes);
    }
}

/*// writes all files of the current directory
Files.list(Paths.get(".")).forEach(System.out::println);*/