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
	,Kedougou;
	
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
		Dakar= new PointRegion(40,270,"nombre de cas positif : 100");
		if(image!=null){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			
			Graphics2D gp= (Graphics2D)g;
			gp.setColor(Color.red);
			gp.fillOval(12, 12, 12, 12);
			gp.fillOval(Dakar.getX(),Dakar.getY(),12,12);
			gp.drawString(Dakar.getNom(), Dakar.getX(), Dakar.getY());
			
		}
	}

}
