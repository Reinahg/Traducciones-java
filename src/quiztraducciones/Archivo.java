package quiztraducciones;



import javax.swing.*;
import java.io.*;

public class Archivo {

    //Metodo para mostrar una ventana que permita elegir un archivo mediante exploracion
    public static String elegirArchivo() {
        JFileChooser fc = new JFileChooser();

        if (fc.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            return f.getAbsolutePath();
        } else {
            return "";
        }
    }

    //Metodo que devuelve la ruta de la carpeta de un archivo
    public static String obtenerCarpeta(String rutaCompleta) {
        File f = new File(rutaCompleta);

        return f.getParent();
    }

    // Método estático para abrir archivos planos
    public static BufferedReader abrirArchivo(String nombreArchivo) {
        File f = new File(nombreArchivo);
        // Existe el archivo?
        if (f.exists()) {
            /*
             * captura de error estructurada. Intenta realizar la instrucción de
             * apertura del archivo. Es susceptible de no poder realizarse
             */
            try {
                /*
                 * Apertura del archivo plano La clase BufferedReader permite
                 * manipular secuencia de caracteres. La clase File ofrece
                 * funcionalidad para operar con archivos
                 */
                FileReader fr = new FileReader(f);
                return new BufferedReader(fr);
            } catch (IOException e) {
                /*
                 * Sucedió un error que se captura mediante la clase IOException
                 * encargada de manipular errores de entrada y salida
                 */
                return null;
            }
        } else {
            return null;
        }
    }//abrirArchivo

    // Método estático para guardar archivos planos dado un conjunto de líneas
    public static boolean guardarArchivo(String nombreArchivo, String[] lineas) {
        if (lineas != null) {
            /*
             * captura de error estructurada. Intenta realizar la instrucción de
             * escritura del archivo. Es susceptible de no poder realizarse
             */
            try {
                //Abrir el archivo para escritura
                BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
                for (int i = 0; i < lineas.length; i++) {
                    //Guardar cada linea
                    bw.write(lineas[i]);
                    bw.newLine();
                }
                //Cerrar el archivo
                bw.close();
                return true;
            } catch (IOException e) {
                /*
                 * Sucedió un error que se captura mediante la clase IOException
                 * encargada de manipular errores de entrada y salida
                 */
                return false;
            }
        } else {
            return false;
        }
    }

    public static String[][] obtenerDatos() {
        //Apertura del archivo
        String nombreArchivo = System.getProperty("user.dir")
                + "/src/datos/Paises y regiones.csv";
        BufferedReader br = abrirArchivo(nombreArchivo);

        if (br != null) {
            try {
                String[] lineas = null;
                //Leer la primera linea del archivo
                String linea = br.readLine();
                while (linea != null) {
                    //Agregar la linea al vector del lineas
                    if (lineas == null) {
                        lineas = new String[1];
                    } else {
                        //Incrementar el tamaño del vector
                        lineas = (String[]) Util.redimensionar(lineas, lineas.length + 1);
                    }
                    lineas[lineas.length - 1] = linea;
                    //Leer siguiente linea
                    linea = br.readLine();
                }
                //cerrar el archivo
                br.close();
                
                String[][] datos=new String[lineas.length][4];
                for(int i=0;i<lineas.length;i++)
                {
                    //Partir la linea en sus partes
                    String[] textos=lineas[i].split(";");
                    for(int j=0;j<4;j++){
                        datos[i][j]=textos[j];
                    }
                }
                return datos;
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }

}
