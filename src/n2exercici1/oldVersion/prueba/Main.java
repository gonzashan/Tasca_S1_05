package n2exercici1.oldVersion.prueba;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        //Carpeta del usuario
        String sCarpAct = System.getProperty("user.dir");
        System.out.println("Carpeta del usuario = " + sCarpAct);

        //Listemos todas las carpetas y archivos de la carpeta actual
        System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

        File carpeta = new File(sCarpAct);
        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");

        }
        else {
            int i=0;
            while (i< listado.length) {
                System.out.println(listado[i]);
                i++;
            }
        }

        //Lo mismo que lo anterior pero con objetos File para poder ver sus propiedades
        System.out.println(ANSI_RED + "//// LISTADO CON OBJETOS File" + ANSI_RESET);

        File[] archivos = carpeta.listFiles();
        if (archivos == null) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        }
        else {

            int i=0;
            while (i< archivos.length) {
                File archivo = archivos[i];
                System.out.printf("%s (%s) - %d - %s%n",
                        archivo.getName(),
                        archivo.isDirectory() ? "Carpeta" : "Archivo",
                        archivo.length(),
                        sdf.format(archivo.lastModified())
                );
                i++;
            }
        }

        //Se pueden filtrar los resultados tanto usando list() como usando listFiles()
        //Por ejemplo, en este segundo caso para mostrar solo archivos y no carpetas
        System.out.println(ANSI_RED + "//// LISTADO CON FILTRO APLICADO - SOLO ARCHIVOS" + ANSI_RESET);

        FileFilter filtro = new FileFilter() {
            @Override
            public boolean accept(File arch) {
                return arch.isFile();
            }
        };

        archivos = carpeta.listFiles(filtro);

        if (archivos == null) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        }
        else {
            int i=0;
            while (i< archivos.length) {
                File archivo = archivos[i];
                System.out.printf("%s (%s) - %d - %s%n",
                        archivo.getName(),
                        archivo.isDirectory() ? "Carpeta" : "Archivo",
                        archivo.length(),
                        sdf.format(archivo.lastModified())
                );
                i++;
            }
        }

    }
}