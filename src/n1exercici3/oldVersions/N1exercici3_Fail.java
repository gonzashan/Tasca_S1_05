package n1exercici3.oldVersions;


import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class N1exercici3_Fail {

    // REVISARLO
    private static final String FOLDER_TO_ACTION = System.getProperty("java.class.path");
    private static final SimpleDateFormat DFI = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        System.out.println(args[0]);

        try{
            File rootContent = new File(args[0]);
            rootContent.createNewFile();
            FileWriter treeMap = new FileWriter("tree_map_directories.txt");
            treeMap.write("\nListado de directorios y archivos en :\n" +  args[0] + "\n");
            displayDirectoriesRecursively(rootContent, treeMap,0);
            treeMap.close();

        } catch (NullPointerException e){

            System.out.println("¡No existe el directorio o la ruta indicada!");

        } catch (Exception e){

            System.out.println(e.getMessage());
        }
    }

    public static void displayDirectoriesRecursively(File rootContent, FileWriter treeMap, int depth) throws IOException {

        //File rootContent = new File(directory);

        if (depth > 0) {
            for (int i = 0; i < depth; i++) {
                System.out.print("   ");
            }

            System.out.print("|__");  //└──
            //treeMap.writingDataIntoFileTxt("|__");
        }

        printDirectoriesAndFiles(rootContent, treeMap);

        if (rootContent.isDirectory()) {
            List<File> elementsFolder = getContentFolder(rootContent);
            // Sort the items from path user gives.
            sortFilesList(elementsFolder);
           /// treeMap.writingDataIntoFileTxt("\t");

            for (File value : elementsFolder) {
                displayDirectoriesRecursively(new File(value.getPath()), treeMap,depth + 1);
            }

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

    private static void printDirectoriesAndFiles(File item, FileWriter treeMap) throws IOException {
        treeMap.write(String.format("\t|__%s [%s] - %5s \n"
                , item.getName()
                , item.isDirectory() ? "Folder" : "File"
                , DFI.format(item.lastModified())
        ));

        System.out.printf(paintAsColor(item)
                        + "%s "
                        + "\033[0m"
                        + "\t[" + paintAsColor(item)+ "%s" + "\033[0m" + "] - %5s \n"
                , item.getName()
                , item.isDirectory() ? "Folder" : "File"
                , DFI.format(item.lastModified())
        );
    }

    // Use it to paint folders and files
    private static String paintAsColor(File item) {
        return item.isDirectory() ? "\033[0;36m" : "\033[0;34m";
    }

    public static void sortFilesList(List<File> fileList) {

        // Avoiding accents into the directory on files names.
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


}
