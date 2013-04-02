package iaws.localisation.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import iaws.localisation.data.UtilisateursList;
import iaws.localisation.domain.Utilisateur;
import iaws.localisation.services.LocalisationService;

public class LocalisationServiceImpl implements LocalisationService{

	
	public List<Utilisateur> listerVoisins(String email, double rayon) {
		if (null != email) {
			List<Utilisateur> listVoisins = new ArrayList<Utilisateur>();
			Utilisateur ref = UtilisateursList.getInstance().search(email);
			
			Iterator<Utilisateur> it = UtilisateursList.getInstance().getListeUtilisateur().iterator();
			while (it.hasNext()){
				Utilisateur ut = it.next();
				if (ut.equals(ref)) {
					if (ut.getCoordonnees().estDansLaZone(ut.getCoordonnees(), rayon)) {
						listVoisins.add(ut);
					}
				}
			}
			return listVoisins;
		}
		return null;
	}

}
