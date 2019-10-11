/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiztraducciones;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author valer
 */
public class Frase {

    private String texto;

    public Frase(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
    
    /////////////////
    public static Frase[] frase;
    public static Document dXML;

    public static void obtenerFrases(Document dXML) {
        frase = null;
        //Validar que el documento exista    
        if (dXML != null) {
            NodeList nlArtistas = dXML.getElementsByTagName("Frase");

        //Instanciar el vector
            frase = new Frase[nlArtistas.getLength()];

            //recorrer la lista de nodos encontrados
            for (int i = 0; i < nlArtistas.getLength(); i++) {
                //Traer el nodo principal u obtener el nodo Frase
                if (nlArtistas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                }
                Element nodo = (Element) nlArtistas.item(i);
                //obtener el nodo del nombre
                NodeList nl = nodo.getElementsByTagName("Texto");
                String texto = nl.item(0).getTextContent();

                //Agregar a la lista
                frase[i] = new Frase(texto);
            }

        }

    }

    public static void mostrar(JComboBox cmb) {
        cmb.removeAllItems();
        if (frase != null) {
            for (int i = 0; i < frase.length; i++) {
                cmb.addItem(frase[i].getTexto());
            }
        }

    }
}
