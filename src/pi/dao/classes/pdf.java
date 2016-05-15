/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pi.technique.DataSource;

/**
 *
 * @author akoubi
 */
public class pdf {

    public static String name = "Ben Romdhane";
    public static String prenom = "mahdi";
    public static String Age = "22";
    public static String Specialite = "développeur";
private Connection connection;

    public pdf() {
        connection = DataSource.getInstance().getConnection();
    }
    public void creerPdf() throws DocumentException, FileNotFoundException, BadElementException, IOException, SQLException {
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Listedesmembresmahdi.pdf"));
        document.open();
        //Image img = Image.getInstance("C:\\Users\\akoubi\\Desktop\\pfl.jpeg");
        //img.scaleAbsolute(50, 50);
        //img.setAbsolutePosition(500f, 750f);
        //document.add(img);
         String requete = "select * from user";
         Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
          while (resultat.next()) {
       // document.add(new Paragraph("id = "+resultat.getInt(1)));
        //document.add(new Paragraph("nom: " + resultat.getString(18)));
        //document.add(new Paragraph("Prénom: " + resultat.getString(19)));
        //document.add(new Paragraph("Nom Utilisateur: " + resultat.getString(20)));
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph("id = "+resultat.getInt(1)));
        PdfPCell cell2 = new PdfPCell();
        cell2.addElement(new Paragraph("nom: " + resultat.getString(18)));
        PdfPCell cell3 = new PdfPCell();
        cell3.addElement(new Paragraph("Prénom: " + resultat.getString(19)));
        PdfPCell cell4 = new PdfPCell();
        cell4.addElement(new Paragraph("Nom Utilisateur: " + resultat.getString(19)));
        PdfPCell cell5 = new PdfPCell();
        cell5.addElement(new Paragraph("adresse " + resultat.getString(26)));
        PdfPCell cell6 = new PdfPCell();
        cell6.addElement(new Paragraph("rib: " + resultat.getString(34)));
        table.addCell(cell);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        document.add(table);
          }
        document.close();
       
    }

}
