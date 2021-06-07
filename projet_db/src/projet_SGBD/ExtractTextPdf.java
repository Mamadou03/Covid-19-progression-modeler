package projet_SGBD;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
public class ExtractTextPdf {

    
	public static void main(String[] args) throws Exception {
    	
		
	    DomPars t= new DomPars();//
	ExtractTextPdf demo = new ExtractTextPdf();
	new Importer();
  
	  demo.run();
	 
  

    }

	String  run() throws Exception {
		   String text="";
	   	 String[] pathnames;
	     File f = new File("C:/Users/JIbri/Desktop/db/saves/");
	     pathnames = f.list();
	     for (String chemin:pathnames) {
	    	 String cheminC= "C:/Users/JIbri/Desktop/db/saves/"+chemin;
		PDDocument document = PDDocument.load(new File(cheminC));
		text = extractTextFromScannedDocument(document);
		System.out.println("c est quel : "+ chemin);
		DomPars t= new DomPars();
		//t.Datcom(text);
		//t.cascommunautaire(text);
		t.Jsonr(text);
		PrintWriter out1 = null;
			try {
	    String nomFichier =chemin.substring(chemin.lastIndexOf('/') + 1);

		    out1 = new PrintWriter(new File("C:/Users/JIbri/Desktop/db/text/"+nomFichier +".txt"));
		    out1.println(text.toString());
		} catch (IOException e) {
		    System.err.println(e);
		} finally {
		    if (out1 != null) {
		        out1.close();
		    }
		}
		
		//System.out.println("qsdfghjklmlkjhgfdsqsdfghjklmùlkjhgfdsq"+text);
	     }
		return text;
		
		
	    }
    private static String extractTextFromScannedDocument(PDDocument document) throws IOException, TesseractException {
    	
	// Extract images from file
	PDFRenderer pdfRenderer = new PDFRenderer(document);
	StringBuilder out = new StringBuilder();
	
	ITesseract _tesseract = new Tesseract();
	_tesseract.setDatapath("C:/Users/JIbri/Desktop/db/Tess4J/tessdata");
	_tesseract.setLanguage("eng");

	for (int page = 0; page < document.getNumberOfPages(); page++) {
	    BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

	    // Create a temp image file
	    File tempFile = File.createTempFile("C:/Users/JIbri/Desktop/db/" + page, ".png");
	    ImageIO.write(bufferedImage, "png", tempFile);
   
	    String result = _tesseract.doOCR(tempFile);
	    out.append(result);
	    // Delete temp file
	    //tempFile.delete();
         
	}
	  String c=out.toString();
	 //System.out.println("oN VA "+c);

	PrintWriter out1 = null;
	
   
	return c;

    }
    
    
    public String casDep(String c) {
		String val="";
		  String dep="(\\d{2,}\\s+a\\s\\w*\\s*.*\\s*;$)";
	   		//"(-[0-9]{2,}+a\\w+.+);$";
	   		
	   Pattern patt=Pattern.compile(dep,Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
	   Matcher mat= patt.matcher(c);
	   if(mat.find()) {
		   
		   String  result_chiffre  = mat.group().replace(" a","");
		   String  s= result_chiffre.replace(" cas","");
	   	//  String g=s.replace(" ","");
	     	String r=s.replace(",","");
	     	String z=r.replace("et","");
	     	
	   	val+=" "+z;
		  
	  	// System.out.println("Je lai enfin trouver" + mat.group());
	   }  		
	  
	   return val;
	}

    
}