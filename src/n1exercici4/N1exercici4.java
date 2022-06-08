package n1exercici4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class N1exercici4 {

    ///Users/escarlata/Desktop/apuntes-java/libraries_&_app/hibernate-validator-7.0.0.Final/project/build-config/src/main/resources/forbidden-allow-junit.txt
    private static final String GET_PATH_TXT = "./src/resources/";
    private static final String GET_FILE_TXT = "tree_map_folders.txt";

    private static String argsPath = System.getProperty("java.class.path");

    public static void main(String[] args) {

        String useForConsole = "";

        if (args.length > 0) {
            // read from console arguments
            readTxtFile(args[0]);

        } else {
            // Checking if console calls file
            if (System.getProperty("user.dir").contains("out")) {

                useForConsole = System.getProperty("user.dir").split("out")[0]
                        + GET_PATH_TXT.replace(".","") + GET_FILE_TXT;
                readTxtFile(useForConsole);
            } else {

                readTxtFile(GET_PATH_TXT + GET_FILE_TXT);
            }
        }
    }

    public static void readTxtFile(String fileTxt) {

        try {// one line to read the whole fileTxt by command line.
            System.out.println(new String(
                    Files.readAllBytes(
                            Paths.get(fileTxt)))
            );
        } catch (IOException e) {

            System.out.println(e.getMessage() + " :Archivo no existe!");

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Error: No ha incluido el nombre del archivo en la línea de comandos!");
        }

    }

}













/*
FileInputStream input = new FileInputStream("C:/logs.txt");
FileChannel channel = input.getChannel();
byte[] buffer = new byte[256 * 1024];
ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

try {
    for (int length = 0; (length = channel.read(byteBuffer)) != -1;) {
        System.out.writingDataIntoFileTxt(buffer, 0, length);
        byteBuffer.clear();
    }
} finally {
    input.close();
}  */
