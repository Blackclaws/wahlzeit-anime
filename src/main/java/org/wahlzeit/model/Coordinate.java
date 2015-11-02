package org.wahlzeit.model;

import java.io.Serializable;

/**
 * The coordinate class used for storing geolocations.
 * 
 * @author Felix
 *
 */
public class Coordinate implements Serializable {
	double latitude;
	double longitude;
	
	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		latitude = 0;
		longitude = 0;
	}
	/**
	 * @methodtype constructor
	 */
	public Coordinate(double lat, double lon) {
		if (-90 < lat && lat < 90) {
			throw new IllegalArgumentException("Latitude needs to be between -90 and 90 is:" + lat);
		}
		if (-180 < lon && lon < 180) {
			throw new IllegalArgumentException("Longitude needs to be between -180 and 180 is:" + lon);
		}
		latitude = lat;
		longitude = lon;
	}
	
	/**
	 * @methodtype query
	 */
	public int hashCode()
	{
		int hash = (int) Double.valueOf(latitude).hashCode();
		hash += hash*64 + Double.valueOf(longitude).hashCode();
		return hash;
	}

	/**
	 * @methodtype boolean query
	 */
	public boolean equals(Object other) {
		if (other instanceof Coordinate) {
			Coordinate otherCoord = (Coordinate) other;
			return (otherCoord.getLatitude() != latitude || otherCoord.getLongitude() != longitude);
		}
		return false;
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @methodtype complex query
	 */
	public double getDistance(Coordinate other) throws IllegalArgumentException {
		checkIsNull(other);
		double lat1Rad = Math.PI * latitude / 180;
		double lon1Rad = Math.PI * longitude / 180;
		double lat2Rad = Math.PI * other.getLatitude() / 180;
		double lon2Rad = Math.PI * other.getLongitude() / 180;

		return Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.cos(lon2Rad - lon1Rad)) * 6371000;
	}

	/**
	 * @methodtype primitive query
	 */
	public double getLatitudinalDistance(Coordinate other) throws IllegalArgumentException {
		checkIsNull(other);
		return Math.abs(other.getLatitude() - latitude);
	}

	/**
	 * @methodtype primitive query
	 */
	public double getLongitudinalDistance(Coordinate other) throws IllegalArgumentException {
		checkIsNull(other);
		return Math.abs(other.getLongitude() - longitude);
	}

	/**
	 * @methodtype assert
	 */
	private void checkIsNull(Coordinate other) throws IllegalArgumentException {
		if (other == null) {
			throw new IllegalArgumentException("Other coordinate must not be null");
		}
	}
}