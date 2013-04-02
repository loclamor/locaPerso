package iaws.localisation.ws.contractfirst;

import java.util.Iterator;
import java.util.List;

import iaws.localisation.data.UtilisateursList;
import iaws.localisation.domain.Utilisateur;
import iaws.localisation.services.LocalisationService;
import iaws.localisation.services.impl.InscriptionServiceImpl;
import iaws.localisation.services.impl.LocalisationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

//import com.mycompany.hr.service.HumanResourceService;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

@Endpoint
public class LocalisationEndpoint {

	private LocalisationService localisationService;
	
	@Autowired
	public LocalisationEndpoint() throws JDOMException {

	}

	@PayloadRoot(namespace = "http://localisationPersonnel.com/rechercheProche/schema", localPart = "rechercheUtilisateursProchesRequest")
	@ResponsePayload
	public Element handleRechercheUtilisateurRequest(@RequestPayload Element rechercheUtilisateursProchesRequest)
			throws Exception
	{
		LocalisationServiceImpl localisationService = new LocalisationServiceImpl();
		
		XPath expEmail = XPath.newInstance("//lp:identifiant");
		expEmail.addNamespace("lp", "http://localisationPersonnel.com/rechercheProche/schema");
		String email = expEmail.valueOf(rechercheUtilisateursProchesRequest);
		
		XPath expRayon = XPath.newInstance("//lp:rayonRecherche");
		expRayon.addNamespace("lp", "http://localisationPersonnel.com/rechercheProche/schema");
		double rayon = Double.parseDouble(expRayon.valueOf(rechercheUtilisateursProchesRequest));
		
		List<Utilisateur> users = localisationService.listerVoisins(email, rayon);
		
		Element rechercheReponseBase = new Element("rechercheUtilisateursProchesResponse");
		if(users != null)
		{
			Iterator<Utilisateur> itu = users.iterator();
			
			while(itu.hasNext()) {
				Utilisateur user = itu.next();
				
				Element rechercheReponseUtilisateur = new Element("utilisateurProche");
				
				Element rechercheReponseUtilisateurNom = new Element("nom");
				rechercheReponseUtilisateurNom.setText(user.getNom());
				rechercheReponseUtilisateur.addContent(rechercheReponseUtilisateurNom);
				
				Element rechercheReponseUtilisateurPrenom = new Element("prenom");
				rechercheReponseUtilisateurPrenom.setText(user.getPrenom());
				rechercheReponseUtilisateur.addContent(rechercheReponseUtilisateurPrenom);
				
				Element rechercheReponseUtilisateurEmail = new Element("adresseEmail");
				rechercheReponseUtilisateurEmail.setText(user.getAdresseEmail());
				rechercheReponseUtilisateur.addContent(rechercheReponseUtilisateurEmail);
				
				Element rechercheReponseUtilisateurAdresse = new Element("adressePostale");
				rechercheReponseUtilisateurAdresse.setText(user.getAdressePostale());
				rechercheReponseUtilisateur.addContent(rechercheReponseUtilisateurAdresse);
				
				rechercheReponseBase.addContent(rechercheReponseUtilisateur);
			}
		}
		
		return rechercheReponseBase;

	}
	
	
	@PayloadRoot(namespace = "http://localisationPersonnel.com/inscription/schema", localPart = "inscriptionUtilisateurRequest")
	@ResponsePayload
	public Element handleInscriptionUtilisateurRequest(@RequestPayload Element inscriptionUtilisateurRequest)
			throws Exception
	{
		InscriptionServiceImpl inscriptionService = new InscriptionServiceImpl();
		Utilisateur user = new Utilisateur();
		
		XPath expNom = XPath.newInstance("//lp:nom");
		expNom.addNamespace("lp", "http://localisationPersonnel.com/inscription/schema");
		user.setNom(expNom.valueOf(inscriptionUtilisateurRequest));
		
		XPath expPrenom = XPath.newInstance("//lp:prenom");
		expPrenom.addNamespace("lp", "http://localisationPersonnel.com/inscription/schema");
		user.setPrenom(expNom.valueOf(inscriptionUtilisateurRequest));
		
		XPath expEmail = XPath.newInstance("//lp:adresseEmail");
		expEmail.addNamespace("lp", "http://localisationPersonnel.com/inscription/schema");
		user.setAdresseEmail(expEmail.valueOf(inscriptionUtilisateurRequest));
		
		XPath expAdresse = XPath.newInstance("//lp:adressePostale");
		expAdresse.addNamespace("lp", "http://localisationPersonnel.com/inscription/schema");
		user.setAdressePostale(expNom.valueOf(inscriptionUtilisateurRequest));
		
		int responseCode = 0;
		responseCode = inscriptionService.inscrireUtilisateur(user);
		
		//TODO: obtenir les coordonnées à partir de openStreetMap
		//responseCode = openStreetMap.verifAdresse(user.adresse);
		
		//creation du XML de reponse
		Element inscriptionReponseBase = new Element( "inscriptionUtilisateurResponse" );
		Element inscriptionReponseReponse = new Element( "reponse" );
		
		if( responseCode > 0 )
		{
			inscriptionReponseReponse.setText( "KO" );
			inscriptionReponseBase.addContent( inscriptionReponseReponse );
			
			Element inscriptionReponseErreur = new Element( "erreur" );
			Element inscriptionReponseErreurCode = new Element( "code" );
			inscriptionReponseErreurCode.setText( String.valueOf( responseCode ) );
			Element inscriptionReponseErreurDescription = new Element( "description" );
			
			switch( responseCode )
			{
				case 100:
					inscriptionReponseErreurDescription.setText("Adresse email déjà utilisée");
					break;
				case 110:
					inscriptionReponseErreurDescription.setText("Adresse email invalide");
					break;
				case 200:
					inscriptionReponseErreurDescription.setText("Adresse postale non connue de Open Street Map");
					break;
				default:
					inscriptionReponseErreurDescription.setText("Une erreur est survenue");
			}
			
			inscriptionReponseErreur.addContent( inscriptionReponseErreurCode );
			inscriptionReponseErreur.addContent( inscriptionReponseErreurDescription );
			inscriptionReponseBase.addContent( inscriptionReponseErreur );
		}
		else
		{
			inscriptionReponseReponse.setText( "OK" );
			inscriptionReponseBase.addContent( inscriptionReponseReponse );
		}
		
		
		return inscriptionReponseBase;
	}

}
