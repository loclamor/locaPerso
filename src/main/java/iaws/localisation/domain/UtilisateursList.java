package iaws.localisation.domain;

import java.util.List;

public class UtilisateursList {
	
	private List<Utilisateur> utilisateurs;
	static UtilisateursList instance = null;
	
	private UtilisateursList() {
		
	}
	
	public static UtilisateursList getInstance() {
		if(instance == null){
			instance = new UtilisateursList();
		}
		return instance;
	}
	
	public List<Utilisateur> getListeUtilisateur() {
		return this.utilisateurs;
	}
	
	public void ajouter(Utilisateur u) {
		this.utilisateurs.add(u);
	}
	
	public Utilisateur getItem(int index) {
		return this.utilisateurs.get(index);
	}
	
	public int count() {
		return this.utilisateurs.size();
	}
}
