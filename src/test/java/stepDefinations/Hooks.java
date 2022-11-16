package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
     @Before("@DeletePlace")
     public void beforeDeleteScenario() throws IOException {
    	 StepDefination sd = new StepDefination();
    	 if(StepDefination.placeID==null)
 		{
    	 sd.add_place_payload_with("test", "English", "Africa");
    	 sd.user_call_with_post_http_request("addPlaceAPI", "post");
    	 sd.verify_place_id_created_maps_to_using_with_http_request("test", "getPlaceAPI","get");
 		}
     }
}
