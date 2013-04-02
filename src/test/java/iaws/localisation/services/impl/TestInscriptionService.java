package iaws.localisation.services.impl;

import iaws.localisation.domain.Utilisateur;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestInscriptionService extends TestCase{
	
	private InscriptionServiceImpl is;
	
	
	public TestInscriptionService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static Test suite()
    {
        return new TestSuite(TestInscriptionService.class);
    }

	public void setUp() throws Exception {
		 super.setUp();
	 }
	
	 public void tearDown() throws Exception {
		 super.tearDown();
	 }
	 
	 public void testInscrireUtilisateur() {
		 is = new InscriptionServiceImpl();
		 Utilisateur u1 = new Utilisateur();
		 u1.setNom("BLANC");
		 u1.setPrenom("Michel");
		 u1.setAdresseEmail("michel.blanc@univ-tlse3.fr");
		 u1.setAdressePostale("118 route de Narbonne, TOULOUSE");
		 assertEquals("inscription OK", 0, is.inscrireUtilisateur(u1));
		 
		 Utilisateur u2 = new Utilisateur();
		 u2.setNom("DEPARDIEU");
		 u2.setPrenom("Gérard");
		 u2.setAdresseEmail("michel.blanc@univ-tlse3.fr");
		 u2.setAdressePostale("118 route de Narbonne, TOULOUSE");
		 assertEquals("inscription : email deja utilise", 100, is.inscrireUtilisateur(u2));
		 
		 Utilisateur u3 = new Utilisateur();
		 u3.setNom("DEPARDIEU");
		 u3.setPrenom("Gérard");
		 u3.setAdresseEmail("gerard.depardieu@gmail.com");
		 u3.setAdressePostale("118 route de Narbonne, TOULOUSE");
		 assertEquals("inscription : email invalide", 110, is.inscrireUtilisateur(u3));
		 
		 Utilisateur u4 = new Utilisateur();
		 u4.setNom("DEPARDIEU");
		 u4.setPrenom("Gérard");
		 u4.setAdresseEmail("gerard.depardieu@gmail.com");
		 u4.setAdressePostale("rue nimporteou, 31000 TOULOUSE");
		 assertEquals("inscription : adresse postale inconnue", 200, is.inscrireUtilisateur(u4));
	 }
	 
}
