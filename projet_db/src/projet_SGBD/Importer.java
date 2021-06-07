package projet_SGBD;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sourceforge.tess4j.Tesseract;



public class Importer extends JFrame implements ItemListener {
	
	 // initialisation des variables
		static ArrayList<String> pdfList; 
		
		static ArrayList<String> liste= new ArrayList<String>();
		private static final long serialVersionUID = 1L;
	    private JPanel panel1;
	    private JPanel panel2;
	    private JPanel panel3;

		private static JButton telecharger , toutCharger, afficher;
		private JLabel label1, label2;
		public static AfficherCarte carte;
	   


	   	public Importer () {
	   		// constructeur du Frame principal
	   		
		AfficherCarte panel = new AfficherCarte();
		// instanciation carte
		try {
			panel.setImage("C:/Users/JIbri/Desktop/db/image.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		liste = getPDFLinks();
		String[] listeurll = liste.toArray(new String[liste.size()]);
	    JComboBox<String> cb=new JComboBox<String>(listeurll); 
	    JComboBox<String> cb1=new JComboBox<String>(listeurll); 

	    cb.setBounds(800,900, 50,30);
	    cb.addItemListener(null);
	    
	    cb.setBounds(800,900, 50,30);

	    	//_____________________________________________________
			this.setVisible(true);
			this.setSize(1200,700);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.getContentPane().setLayout(null);
			//____________________________________________________
			telecharger = new JButton("Telecharger");
			telecharger.setBounds(775,150, 150,30);
			telecharger.setFont(new Font("Monospaced", Font.BOLD, 12));

			toutCharger = new JButton("Tout telecharger");
			toutCharger.setBounds(975,150, 150,30);
			toutCharger.setFont(new Font("Monospaced", Font.BOLD, 12));

			afficher = new JButton("Afficher");
			afficher.setBounds(860, 560, 150, 30);
			afficher.setFont(new Font("Monospaced", Font.BOLD, 12));
			telecharger.addActionListener(new ActionListener() {  

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String s =cb.getItemAt(cb.getSelectedIndex());
					try {
						tel(s);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(s);
					
				}  
		        	});           
	    	
			toutCharger.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						tel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(s);
					
				}  
		        	});
			afficher.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub     
				}
		        	});   

			label1= new JLabel();
			label1.setText("Selectionner pdf a telecharger.");
			label1.setFont(new Font("Monospaced", Font.BOLD, 12));
			label1.setBounds(800, 800, 75, 30);
		
			label2= new JLabel();
			label2.setText("Selectionner le mois a afficher");
			label2.setFont(new Font("Monospaced", Font.BOLD, 12));
			label2.setBounds(800, 800, 75, 30);

			panel1=new JPanel();
			panel1.setVisible(true);
			panel1.setBackground(Color.WHITE);
			panel1.setBounds(10, 10, 500, 500);

			panel2=new JPanel();
			panel2.setVisible(true);
			panel2.setBackground(Color.LIGHT_GRAY);
			//panel2.setPreferredSize(new Dimension(400,100));
			panel2.setBounds(new Rectangle(750, 10, 400, 100));
			
			panel3=new JPanel();
			panel3.setVisible(true);
			panel3.setBackground(Color.lightGray);
			panel3.setBounds(new Rectangle(750, 350, 400, 100));

			this.add(telecharger);
			this.add(toutCharger);
			this.add(cb);
			this.add(cb1);
			panel2.add(cb);
			panel2.add(label1);
			panel3.add(cb1);
			panel3.add(label2);
			this.add(panel2);
			this.add(panel3);
			this.add(afficher);
			this.getContentPane().add(panel);
			
	}

	public static ArrayList<String> getPDFLinks() {
			
	        pdfList = new ArrayList<String>(); 
	  		String url= "https://sante.sec.gouv.sn/taxonomy/term/14?page=2";
	          String regex = "sites/default/com";
	          CloseableHttpClient client = HttpClientBuilder.create().build();
	  		HttpGet request = new HttpGet(url);
	  		HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		HttpEntity entity = response.getEntity();
	  		try {
				InputStream inputStream = entity.getContent();
			} catch (UnsupportedOperationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	          Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
	          try {
	  			Document document = Jsoup.connect(url).get();
	  	        Elements links =   document.select("a[href]");
	  	        for (Element content : links)
	  	        {
	  	            String relHref = content.attr("href");
	  	            Matcher matcher = pattern.matcher(relHref);
	  	            boolean matchFound = matcher.find();

	  	            if(matchFound) {
	  	            	pdfList.add(relHref);
	  	            	  
	  	              } else {
	  	              }
	  	            
	  	        }

	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}

	  		return pdfList;
	  		
	  	}
	    

		
	public static void  tel() throws IOException{
		for(int i= 0; i< liste.size();i++) {		
		String nomFichier = liste.get(i).substring(liste.get(i).lastIndexOf('/') + 1);
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(liste.get(i));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();

		try (FileOutputStream fos = new FileOutputStream("C:/Users/JIbri/Desktop/db/saves/"+nomFichier)) {
			int bytedata;
			while((bytedata = inputStream.read()) != -1) {
			    fos.write(bytedata);
			}
		}	
	}

	}

	public static void  tel(String s) throws IOException{
		
			
			String nomFichier =s.substring(s.lastIndexOf('/') + 1);
		
		CloseableHttpClient client = HttpClientBuilder.create().build();

		HttpGet request = new HttpGet(s);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();

		try (FileOutputStream fos = new FileOutputStream("C:/Users/JIbri/Desktop/db/saves/"+nomFichier)) {
			int bytedata;
			while((bytedata = inputStream.read()) != -1) {
			    fos.write(bytedata);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
		
     	
		

}