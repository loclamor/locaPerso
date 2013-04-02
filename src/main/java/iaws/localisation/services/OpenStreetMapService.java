package iaws.localisation.services;

import iaws.localisation.domain.Coordonnees;

public interface OpenStreetMapService {
	public Coordonnees getCoordonneesAdresse(String adresse);
}
