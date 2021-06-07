package projet_SGBD;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AfficherCarte extends JPanel {
	public PointRegion Dakar, Stlouis, Matam, Kolda,Louga,Thies, Kaolack,Tamba,zig, Diourbel, Fatick, Sedhiou
	,Kedougou, kafrine;
	
	private static final long serialVersionUID = -6350324456764768311L;
	
	private Image image;

	public AfficherCarte(Image image) {
		super();
		setImage(image);
	}

	public AfficherCarte(String path) throws IOException {
		super();
		setImage(path);
	}
	
	public AfficherCarte() {
		
		this.setSize(new Dimension(700,600));
		this.setVisible(true);
	}

	
	public void setImage(Image image) {
		this.image = image;
		repaint();
	}
	
	
	public void setImage(String path) throws IOException {
		try {
			this.image = ImageIO.read(new File(path));
			repaint();
		} 
		catch (IOException e) {
			throw new IOException(path+" introuvable", e);
		}
	}


	public Image getImage() {
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		Dakar= new PointRegion(40,255,"DAKAR : --");// good
		
		Stlouis = new PointRegion(250,140,"SAINT-LOUIS : --");
		Matam = new PointRegion(400,275,"MATAM : --");
		Kolda = new PointRegion(290,470,"KOLDA : --");
		zig = new PointRegion(70,480,"ZIGUINCHOR : --");
		kafrine= new PointRegion(299,350,"KAFFRINE : --");
		
		Diourbel = new PointRegion(100,289,"DIOURBEL : --");
		Louga = new PointRegion(250,185,"LOUGA : --");
		Fatick = new PointRegion(157,350,"FATICK : --");
		Sedhiou = new PointRegion(200,470,"SEDHIOU : --");
		Kedougou = new PointRegion(480,460,"KEDOUGOU : --");
		Thies = new PointRegion(60,300,"THIES : --");
		Kaolack = new PointRegion(200,325,"KAOLACK : --");
		Tamba = new PointRegion(450,320,"TAMBA : --");


		if(image!=null){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			
			Graphics2D gp= (Graphics2D)g;
			gp.setColor(Color.yellow);
			gp.fillOval(Dakar.getX(),Dakar.getY(),15,15);
			gp.drawString(Dakar.getNom(), Dakar.getX(), Dakar.getY());
			
			gp.fillOval(Stlouis.getX(),Stlouis.getY(),15,15);
			gp.drawString(Stlouis.getNom(), Stlouis.getX(), Stlouis.getY());
		
			gp.fillOval(Thies.getX(),Thies.getY(),15,15);
			gp.drawString(Thies.getNom(), Thies.getX(), Thies.getY());
			
			gp.fillOval(Louga.getX(),Louga.getY(),15,15);
			gp.drawString(Louga.getNom(), Louga.getX(), Louga.getY());
			
			gp.fillOval(Matam.getX(),Matam.getY(),15,15);
			gp.drawString(Matam.getNom(), Matam.getX(), Matam.getY());
		
			gp.fillOval(zig.getX(),zig.getY(),15,15);
			gp.drawString(zig.getNom(), zig.getX(), zig.getY());
			
			gp.fillOval(Tamba.getX(),Tamba.getY(),15,15);
			gp.drawString(Tamba.getNom(), Tamba.getX(), Tamba.getY());
			
			gp.fillOval(Diourbel.getX(),Diourbel.getY(),15,15);
			gp.drawString(Diourbel.getNom(), Diourbel.getX(), Diourbel.getY());
			
			gp.fillOval(Sedhiou.getX(),Sedhiou.getY(),15,15);
			gp.drawString(Sedhiou.getNom(), Sedhiou.getX(), Sedhiou.getY());
			
			gp.fillOval(Kolda.getX(),Kolda.getY(),15,15);
			gp.drawString(Kolda.getNom(), Kolda.getX(), Kolda.getY());
			
			gp.fillOval(Kedougou.getX(),Kedougou.getY(),15,15);
			gp.drawString(Kedougou.getNom(), Kedougou.getX(), Kedougou.getY());
			
			gp.fillOval(Fatick.getX(),Fatick.getY(),15,15);
			gp.drawString(Fatick.getNom(), Fatick.getX(), Fatick.getY());
			
			gp.fillOval(kafrine.getX(),kafrine.getY(),15,15);
			gp.drawString(kafrine.getNom(), kafrine.getX(), kafrine.getY());
			
			gp.fillOval(Kaolack.getX(),Kaolack.getY(),15,15);
			gp.drawString(Kaolack.getNom(), Kaolack.getX(), Kaolack.getY());
			
		
		}
	}

}
