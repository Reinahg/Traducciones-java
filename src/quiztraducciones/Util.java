package quiztraducciones;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Util {

    public static Object redimensionar(Object arregloOriginal, int nuevoTamano) {
        Object nuevoArreglo = null;
        if (arregloOriginal != null) {
            Class tipo = arregloOriginal.getClass();
            Class t = tipo.getComponentType();
            nuevoArreglo = Array.newInstance(t, nuevoTamano);
            System.arraycopy(arregloOriginal, 0, nuevoArreglo, 0, Math.min(Array.getLength(arregloOriginal), nuevoTamano));
        }
        return nuevoArreglo;
    }

    public static void mostrarImagen(JPanel pnl, String nombreArchivo) {
        try {
            //Limpiar el panel
            pnl.removeAll();
            //Definir el archivo de la imagen
            File f = new File(nombreArchivo);
            //Verificar si existe el archivo
            if (f.exists()) {
                //Leer el archivo
                BufferedImage bimg = ImageIO.read(f);
                //Cargar la imagen en un objeto JLABEL
                ImageIcon icon = new ImageIcon(bimg);
                JLabel lbl = new JLabel();
                lbl.setIcon(icon);
                //Definir un panel de desplazamiento
                JScrollPane jsp = new JScrollPane(lbl);
                //Ubicarlo en el panel
                jsp.setBounds(0, 0, pnl.getWidth(), pnl.getHeight());
                //Agregarlo al panel
                pnl.add(jsp);
                pnl.revalidate();
            }
            pnl.repaint();

        } catch (Exception ex) {
        }
    }

}
