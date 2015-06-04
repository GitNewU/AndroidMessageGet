package com.AndroidKernelService;

public class JsonMiniGPS {

	private String access_token = null;
	private MiniGPSLocation location = null;
	
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public MiniGPSLocation getLocation() {
		return location;
	}

	public void setLocation(MiniGPSLocation location) {
		this.location = location;
	}

	public class MiniGPSLocation {
		private String latitude = null;
		private String longitude = null;
		private MiniGPSAddress address = null;
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public MiniGPSAddress getAddress() {
			return address;
		}
		public void setAddress(MiniGPSAddress address) {
			this.address = address;
		}
		
	}
	
	public class MiniGPSAddress {
		private String city = null;
		private String country = null;
		private String country_code = null;
		private String county = null;
		private String postal_code = null;
		private String region = null;
		private String street = null;
		private String street_number = null;
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCountry_code() {
			return country_code;
		}
		public void setCountry_code(String country_code) {
			this.country_code = country_code;
		}
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public String getPostal_code() {
			return postal_code;
		}
		public void setPostal_code(String postal_code) {
			this.postal_code = postal_code;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getStreet_number() {
			return street_number;
		}
		public void setStreet_number(String street_number) {
			this.street_number = street_number;
		}
	}
}