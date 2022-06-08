package n1exercici3.oldVersions;


import n1exercici1.N1exercici1;

import java.io.*;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class N1exercici3_extented {


    private static final String FOLDER_TO_ACTION = System.getProperty("java.class.path");
    private static final SimpleDateFormat DFI = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static int counterDirectories = 0, counterFiles = 0;

    public static void main(String[] args) {

        try {
            File rootContent = new File(FOLDER_TO_ACTION);

            rootContent.createNewFile();

            // Let me delete old file
            FileWriter treeMap = new FileWriter("tree_map_directories.txt");
            writingDataIntoFileTxt("\nListado de directorios y archivos en:\n");
            writingDataIntoFileTxt(FOLDER_TO_ACTION + "\n");
            System.out.println("Directorio analizado: " + rootContent.getName());
            // Go for it
            displayDirectoriesRecursively(rootContent, "\n", rootContent.getName());
            // Creating message to close the actions
            String closingFileTxT = "Carpetas:  " + counterDirectories + "\nFicheros: " + counterFiles;
            System.out.println(closingFileTxT);
            writingDataIntoFileTxt("\n\nElementos recopilados:\n"
                    + "Carpetas:  " + counterDirectories + "\nFicheros: " + counterFiles);

        } catch (FileNotFoundException e) {

            System.out.println("¡No existe el fichero indicado!");

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada!");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void displayDirectoriesRecursively(File rootScanTo, String tab, String root) {

        // Become into List<File> content of reoot to scan
        List<File> elementsFolder = getContentFolder(rootScanTo);
        // Sorted it
        N1exercici1.sortFilesList(elementsFolder);

        if (elementsFolder.size() > 0) {

            for (int i = 0; i < elementsFolder.size(); i++) {
                counterFiles++;
                Date d = new Date(elementsFolder.get(i).lastModified());

                if (elementsFolder.get(i).isFile()) {
                    // System.out.println(tab + arxius[i].getName() + "(F) "+ d);
                    writingDataIntoFileTxt(tab + "|__" + elementsFolder.get(i).getName()
                            + " [Fichero] " + DFI.format(elementsFolder.get(i).lastModified()));

                } else {

                    writingDataIntoFileTxt(tab + "|__" + elementsFolder.get(i).getName()
                            + " [Carpeta] " + DFI.format(elementsFolder.get(i).lastModified()));
                    String levelTabs = tab + "\t";

                    displayDirectoriesRecursively(elementsFolder.get(i), levelTabs, root);

                }
            }
            counterDirectories++;
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


    public static void sortFilesList(List<File> fileList) {

        // Avoiding accents into the directory in files names.
        Collator avoidAccents = Collator.getInstance(new Locale("es"));
        avoidAccents.setStrength(Collator.PRIMARY);

        // Anonymous class
        fileList.sort(new Comparator<>() {
            @Override
            public int compare(File o1, File o2) {

                return avoidAccents.compare(o1.getName(), o2.getName());
            }
        });
    }


    public static void writingDataIntoFileTxt(String text) {

        BufferedWriter grabb;

        try {

            grabb = new BufferedWriter(new FileWriter("./src/resources/tree_map_directories.txt", true));
            grabb.write(text);
            grabb.close();

        } catch (IOException e) {

            System.out.println("Errores de escritura");

        } catch (NullPointerException e) {

            System.out.println("¡No existe el directorio o la ruta indicada!");

        }
    }



}




