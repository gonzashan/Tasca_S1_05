package n1exercici5;

import java.io.IOException;

public class N1exercici5 {

    private static final String GET_PATH_SER = "./src/resources/";
    private static final String GET_FILE_SER = "serializar.ser";
    private static String PATH_AND_FILE = GET_PATH_SER + GET_FILE_SER;

    public static void main(String[] args) {

        String useForConsole = "";

        if (args.length > 0) {
            // read from console arguments
            PATH_AND_FILE = args[0];

        }

        starts();
    }

    public static void starts() {

        try {
            Serializa.serializar(PATH_AND_FILE);
            DesSerializa.desSerializar();

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }



    }
}
