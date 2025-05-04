package javatube.inner;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

public class InnerTube {

	private boolean usePoToken;

	private JSONObject defaultClient = new JSONObject();
	
	@Getter
    private JSONObject innerTubeContext;
    private boolean requireJsPlayer;
    private boolean requirePoToken;
    private JSONObject header;
    private String apiKey;

    private String accessPoToken;
    private String accessVisitorData;
	
    public InnerTube(String client) throws JSONException {
        this(client, false, false);
    }
    
    public InnerTube(String client, boolean usePoToken) throws JSONException {
        this(client, usePoToken, false);
    }
    
    
}


