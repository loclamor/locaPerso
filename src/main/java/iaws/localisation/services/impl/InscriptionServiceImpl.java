package iaws.localisation.services.impl;

import iaws.localisation.data.UtilisateursList;
import iaws.localisation.domain.Coordonnees;
import iaws.localisation.domain.Utilisateur;
import iaws.localisation.services.InscriptionService;

public class InscriptionServiceImpl implements InscriptionService {

	
	public int inscrireUtilisateur(Utilisateur utilisateur) {
		
		//adresse email deja utilisee
		if (UtilisateursList.getInstance().search(utilisateur.getAdresseEmail()) != null)
			return 100;
		
		//adresse email invalide
		if (!utilisateur.getAdresseEmail().endsWith("@univ-tlse3.fr") || !utilisateur.isEmailAdress())
			return 110;
		
		//adresse postale non connue de open street map
		OpenStreetMapServiceImpl osmService = new OpenStreetMapServiceImpl();
		Coordonnees coords = osmService.getCoordonneesAdresse(utilisateur.getAdressePostale());
		if ( coords == null )
			return 200;
		if (!coords.estValide())
			return 200;
		//sauvegarde des informations de l utilisateur et des coordonnees de l adresse postale 
		utilisateur.setCoordonnees(coords);
		UtilisateursList.getInstance().ajouter(utilisateur);
		return 0;
	}

}
