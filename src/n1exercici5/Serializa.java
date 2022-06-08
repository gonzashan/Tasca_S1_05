package n1exercici5;

import java.io.IOException;

public class Serializa {

    private static final String PATH = "./src/resources/serializar.ser";

    public static void main(String[] args) throws IOException {

        if (args[0] != null) {

            serializar(args[0]);

        } else {

            serializar(PATH);
        }


    }

    public static void serializar(String PATH) throws IOException {

        ContactoOutput output = new ContactoOutput();
        output.open(PATH);
        output.writingTo(new Contacto("Gonzalo", "654789645"));
        output.writingTo(new Contacto("Federíc", "677899321"));
        output.writingTo(new Contacto("Oriolet", "807563892"));
        output.close(PATH);

    }


}
