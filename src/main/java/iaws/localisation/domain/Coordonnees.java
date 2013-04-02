package iaws.localisation.domain;

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
		return "Coordonnees [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	/**
	 * 
	 * @param c, coordonnees a comparer
	 * @param rayon, rayon en km
	 * @return comparaison entre la distance en km et le rayon en km
	 */
	public boolean estDansLaZone(Coordonnees c, double rayon) {
		int radius = 6366;
		double lat1 = Math.toRadians(this.latitude);
		double lon1 = Math.toRadians(this.longitude);
		
		double lat2 = Math.toRadians(c.latitude);
		double lon2 = Math.toRadians(c.longitude);
		
		double distance = radius *
							(2 * Math.asin(
									Math.sqrt(
											Math.pow(
														(Math.sin((lat1 - lat2)/2)), 2
													)
											+
											Math.cos(lat1)*Math.cos(lat2) *
												(Math.pow(
															Math.sin(((lon1-lon2)/2)), 2
														 )
												)
											)
										)
							);
		return distance <= rayon;
	}
	
}
