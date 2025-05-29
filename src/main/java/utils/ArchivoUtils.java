package utils;

import java.io.*;
import java.util.List;

public class ArchivoUtils {

    public static void guardarObjetos(List<?> lista, String ruta) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(lista);
        }
    }

    public static Object cargarObjetos(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            return in.readObject();
        }
    }
}
