/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiztraducciones;

import java.text.Normalizer;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import org.w3c.dom.*;


/**
 *
 * @author valer
 */
public class Traduccion {

    private Frase frase;
    private String idioma;
    private String textoTraducido;

    public Traduccion(Frase frase, String idioma, String textoTraducido) {
        this.frase = frase;
        this.idioma = idioma;
        this.textoTraducido = textoTraducido;
    }

    public Frase getFrase() {
        return frase;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTextoTraducido() {
        return textoTraducido;
    }
    ////////////////////////
    public static Traduccion[] traducciones;
    public static Document dXML;
    public static String rutaAudios;

    public void play() {
        
        /**
         * Los nombres de los audios están sin espacios, sin tildes y sin signos, entonces
         * es necesario quitarlos
         */
        
        String fraseEditada = frase.getTexto().replaceAll(" ", ""); //quitar espacios
        
        //quitar tildes
        
        fraseEditada = Normalizer.normalize(fraseEditada, Normalizer.Form.NFD);
        fraseEditada = fraseEditada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        
        //quitar signos
        
        fraseEditada = fraseEditada.replaceAll("[^a-z,^A-Z,^0-9]", "");
        
        String nombreArchivo = rutaAudios + "\\" + fraseEditada + "-" + idioma + ".mp3";
        ReproductorAudio.reproducir(nombreArchivo);
    }

    public static void getTraducciones(Document dXML, Frase f) {
        traducciones = null;
        //Validar que el documento exista    
        if (dXML != null) {
            NodeList nlTraducciones = dXML.getElementsByTagName("Traduccion");
            //recorrer todos los nodos obtenidos
            for (int i = 0; i < nlTraducciones.getLength(); i++) {
                //obtener el nodo padre
                Element nodoDad = (Element) nlTraducciones.item(i).getParentNode();
                //obtener el nodo del nombre
                NodeList nl = nodoDad.getElementsByTagName("Texto");
                String nombreArtist = nl.item(0).getTextContent();
                //verificar que corresponda el nombre al texto que se busca
                if (nombreArtist.equals(f.getTexto())) {

                    Element nodo = (Element) nlTraducciones.item(i);
                    //obtener el nodo del nombre
                    nl = nodo.getElementsByTagName("Idioma");
                    String idioma = nl.item(0).getTextContent();
                    //obtener el nodo del nombre
                    nl = nodo.getElementsByTagName("TextoTraducido");
                    String textoTraducido = nl.item(0).getTextContent();
                    //obtener el nodo del nombre

                    if (traducciones == null) {
                        traducciones = new Traduccion[1];
                    } else {
                        traducciones = (Traduccion[]) Util.redimensionar(traducciones, traducciones.length + 1);
                    }
                    
                    traducciones[traducciones.length - 1] = new Traduccion(f, idioma, textoTraducido);
                }

            }

        }
    }

    public static void mostrar(JComboBox cmb) { //Mostrar en el cmb
        cmb.removeAllItems();
        if (traducciones != null) {
            for (int i = 0; i < traducciones.length; i++) {
                cmb.addItem(traducciones[i].getIdioma());
            }
        }

    }

    public static void mostrarTraduccion(JTextArea ta, int index) { //mostrar en el textArea
        ta.removeAll();
        if (traducciones != null) {
            ta.setText(traducciones[index].getTextoTraducido());
        }
    }
}
