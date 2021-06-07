package projet_SGBD;

public class PointRegion {
private int x, y;
private String nom;


private DomPars  dom;

PointRegion(int x, int y, String nom) {
	
	this.setX(x);
	this.setY(y);
	this.setNom(nom);
	dom=new DomPars();
	
	
}


public int getY() {
	
	return y;
}


public void setY(int y) {
	this.y = y;
}


public int getX() {
	return x;
}


public void setX(int x) {
	this.x = x;
}


public String getNom() {
	
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}
public String toString() {
	
	String afficher= "Region de :"+this.getNom();
	
	return afficher;
	
	
}

}


