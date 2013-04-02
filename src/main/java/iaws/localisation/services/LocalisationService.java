package iaws.localisation.services;

import iaws.localisation.domain.Utilisateur;

import java.util.List;

public interface LocalisationService {
	public List<Utilisateur> listerVoisins(String utilisateur, double rayon);
}
