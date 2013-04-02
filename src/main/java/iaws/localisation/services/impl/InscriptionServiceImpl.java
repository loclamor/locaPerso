package iaws.localisation.services.impl;

import iaws.localisation.domain.Utilisateur;
import iaws.localisation.domain.UtilisateursList;
import iaws.localisation.services.InscriptionService;

public class InscriptionServiceImpl implements InscriptionService {

	@Override
	public int inscrireUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
		//adresse email deja utilisee
		if (UtilisateursList.getInstance().search(utilisateur.getAdresseEmail()) == null)
			return 100;
		//adresse email invalide
		if (!utilisateur.getAdresseEmail().endsWith("@univ-tlse3.fr") || !utilisateur.isEmailAdress())
			return 110;
		//adresse postale non connue de open street map
		if (true)
			return 200;
		//sauvegarde des informations de l utilisateur et des coordonnees de l adresse postale 
		
		return 0;
	}

}
