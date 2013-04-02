package iaws.localisation.ws.contractfirst;

import iaws.localisation.services.LocalisationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

//import com.mycompany.hr.service.HumanResourceService;
import org.jdom.Element;
import org.jdom.JDOMException;

@Endpoint
public class LocalisationEndpoint {

	private LocalisationService localisationService;
	
	@Autowired
	public LocalisationEndpoint() throws JDOMException {

	}

	@PayloadRoot(namespace = "http://localisationPersonnel.com/rechercheProche/schema", localPart = "rechercheUtilisateursProchesRequest")
	public void handleRechercheUtilisateurRequest(@RequestPayload Element rechercheUtilisateursProchesRequest)
			throws Exception {

	}
	
	@PayloadRoot(namespace = "http://localisationPersonnel.com/inscription/schema", localPart = "inscriptionUtilisateurResponse")
	public void handleInscriptionUtilisateurRequest(@RequestPayload Element inscriptionUtilisateurResponse)
			throws Exception {

	}
}
