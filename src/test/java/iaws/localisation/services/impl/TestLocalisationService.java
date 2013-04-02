package iaws.localisation.services.impl;

import iaws.localisation.domain.Utilisateur;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestLocalisationService extends TestCase {

	private LocalisationServiceImpl ls;

	public TestLocalisationService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static Test suite()
    {
        return new TestSuite(TestLocalisationService.class);
    }

	public void setUp() throws Exception {
		 super.setUp();
	 }
	
	 public void tearDown() throws Exception {
		 super.tearDown();
	 }
	 
	 public void testListerVoisins() {
		 ls = new LocalisationServiceImpl();
		 List<Utilisateur> users = ls.listerVoisins("garrett.W@univ-tlse3.fr", 5);
		 assertNull(users);
	 }
}
