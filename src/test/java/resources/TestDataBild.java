package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBild {

	public AddPlace addPlaceDataLoad(String name,String language,String address) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setName(name);
		ap.setWebsite("http://google.com");

		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		ap.setTypes(typesList);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		
		return ap;
	}
	
	public String deletePlacePayload (String placeID) {
		return "{\r\n \"place_id\":\""+placeID+"\"\r\n}";
	}
}
