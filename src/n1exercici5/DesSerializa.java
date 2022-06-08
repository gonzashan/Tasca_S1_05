package n1exercici5;

import java.io.EOFException;
import java.io.IOException;

public class DesSerializa {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        desSerializar();
    }

    public static void desSerializar() throws IOException, ClassNotFoundException {


        Contacto contacto;
        ContactoInput input;
        input = new ContactoInput();
        input.open();

        try {
            System.out.println("\nArchivo Des-Serializado\n" +
                    "----------------------------------------------------");
            do {

                contacto = input.read();
                System.out.printf("%-10s: %s %20s: %s\n",
                        "Nombre","\033[0;33m" + contacto.getName() + "\033[0m",
                        "Telf", "\033[0;33m" + contacto.getPhoneNumber() + "\033[0m");

            } while (contacto != null);

        } catch (EOFException e) {
            //
            //System.out.println("pasa esto: " + e.getMessage());
        }

        input.close();


    }

}
