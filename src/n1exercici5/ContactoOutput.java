package n1exercici5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ContactoOutput {

    private FileOutputStream file;
    private ObjectOutputStream output;
    private int counter = 0;

    // Abrir el fichero
    public void open(String PATH) throws IOException {

        file = new FileOutputStream(PATH);
        output = new ObjectOutputStream(file);
    }

    // Cerrar el fichero
    public void close(String PATH) throws IOException {

        if (output != null) {

            System.out.println("\nFichero '" + PATH + "' serializado con "
                    + counter + " registros.");
            output.close();
        }
    }

    // Escribir en el fichero
    public void writingTo(Contacto contacto) throws IOException {

        if (output != null) {

            output.writeObject(contacto);
            counter++;
        }
    }

}