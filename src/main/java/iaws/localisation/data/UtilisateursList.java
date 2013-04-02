package iaws.localisation.data;

import iaws.localisation.domain.Utilisateur;

import java.util.Iterator;
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
	
	public Utilisateur search(String email) {
		
		Iterator<Utilisateur> itu = this.utilisateurs.iterator();
		Utilisateur utilisateurTrouve = null;
		while(itu.hasNext()){
			Utilisateur utilisateurCourant = itu.next();
			if(utilisateurCourant.getAdresseEmail().equals(email)){
				utilisateurTrouve = utilisateurCourant;
			}
		}
		
		return utilisateurTrouve;
		
	}
}
