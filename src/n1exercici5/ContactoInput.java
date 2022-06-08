package n1exercici5;

import java.io.*;

public class ContactoInput {

    private FileInputStream file;
    private ObjectInputStream input;

    // Abrir el fichero
    public void open() {

        try {

            file = new FileInputStream("./src/resources/serializar.ser");
            input = new ObjectInputStream(file);

        } catch (IOException e) {

            System.out.println("No se encuentra " + e.getCause());
        }

    }

    // Cerrar el fichero
    public void close() throws IOException {

        try {

            if (input != null)
                input.close();

        } catch (IOException e) {

            System.out.println("No se encuentra " + e.getCause());
        }
    }


    public Contacto read() throws IOException, ClassNotFoundException {

        Contacto contacto = null;

        if (input.read() == -1) {

                contacto = (Contacto) input.readObject();
        }

        return contacto;
    }
}