package iaws.localisation.ws.domain;

public class Coordonnees {
	private double latitude;
	private double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Coordonnes [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
}
