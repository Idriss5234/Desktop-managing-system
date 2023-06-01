package databases;

import java.util.ArrayList;
public class Dossier extends Element{
	private ArrayList<Element> listeElements = new ArrayList();	
	public ArrayList<Element> getListeElements() {
		return listeElements;
	}
	public void setListeElements(ArrayList<Element> listeElements) {
		this.listeElements = listeElements;
	}
	public int getTaille() {
		int taille=0;
		for(Element e: listeElements  ) {
			taille+=e.getTaille();
		}
		return taille;
	}
	public Dossier(ArrayList<Element> listeElements) {
		super();
		this.listeElements = listeElements;
	}
}
