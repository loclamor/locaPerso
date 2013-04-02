package iaws.localisation.ws.domain;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String adresseEmail;
	private String adressePostale;
	private Coordonnees coordonnees;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresseEmail() {
		return adresseEmail;
	}
	public void setAdresseEmail(String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}
	public String getAdressePostale() {
		return adressePostale;
	}
	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}
	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom
				+ ", adresseEmail=" + adresseEmail + ", adressePostale="
				+ adressePostale + "]";
	}
	
	
	
}
