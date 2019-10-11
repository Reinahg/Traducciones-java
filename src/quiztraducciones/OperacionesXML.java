/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiztraducciones;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;

/**
 *
 * @author salas
 */
public class OperacionesXML {

    public static Document abrirDocumentoXML(String nombreArchivo) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(nombreArchivo);
        } catch (ParserConfigurationException pce) {
            return null;
        } catch (SAXException se) {
            return null;
        } catch (IOException ioe) {

            return null;
        }
    }

}
