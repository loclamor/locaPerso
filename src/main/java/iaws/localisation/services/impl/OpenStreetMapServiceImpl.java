package iaws.localisation.services.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import iaws.localisation.domain.Coordonnees;
import iaws.localisation.services.OpenStreetMapService;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.Document;
import org.jdom.Element;

import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;

public class OpenStreetMapServiceImpl implements OpenStreetMapService {

	public Coordonnees getCoordonneesAdresse(String adresse) {

		HttpClient httpclient = new DefaultHttpClient();
		String reponse = null;

		try {
			HttpGet httpget = new HttpGet(
					"http://nominatim.openstreetmap.org/search?format=xml&limit=1&q="
							+ adresse.replace(' ', '+'));
			ResponseHandler<String> gestionnaire_reponse = new BasicResponseHandler();

			try {
				reponse = httpclient.execute(httpget, gestionnaire_reponse);
				System.out.println(reponse);
			} catch (ClientProtocolException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		if (reponse != null) {
			SAXBuilder builder = new SAXBuilder();
			Reader in = new StringReader(reponse);
			
			Document doc;
			Element rootElt, placeElt;
			try {
				doc = builder.build(in);
				rootElt = doc.getRootElement();
				
				placeElt = rootElt.getChild("place");
				
				double lat = placeElt.getAttribute("lat").getDoubleValue();
				double lon = placeElt.getAttribute("lon").getDoubleValue();
				
				return new Coordonnees(lat, lon);

			} catch (JDOMParseException e) {
				// do what you want
			} catch (IOException e) {
				// do what yo want
			} catch (Exception e) {
				// do what you want
			}
		}

		return null;
	}

}
