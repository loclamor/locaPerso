package iaws.localisation.ws.contractfirst;

import iaws.localisation.domain.Utilisateur;
import iaws.localisation.services.LocalisationService;

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
		return rechercheUtilisateursProchesRequest;

	}
	
	
	@PayloadRoot(namespace = "http://localisationPersonnel.com/inscription/schema", localPart = "inscriptionUtilisateurRequest")
	@ResponsePayload
	public Element handleInscriptionUtilisateurRequest(@RequestPayload Element inscriptionUtilisateurRequest)
			throws Exception
	{
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
		//TODO: verifier adresseEmail Unique et Correcte
		//responseCode = LocalisationService.ajouterUtilisateur(user);
		
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
