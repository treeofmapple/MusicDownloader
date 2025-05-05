package javatube.inner;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

public class InnerTube {

	@Getter
    private JSONObject innerTubeContext;
    private boolean requireJsPlayer;
    private boolean requirePoToken;
    private JSONObject header;
    private String apiKey;

    private final boolean usePoToken;
    private String accessPoToken;
    private String accessVisitorData;

	public InnerTube(String client, boolean usePoToken, boolean allowCache) throws JSONException {
        JSONObject defaultClient = InnerTubeUtils.loadDefaultClient();
        JSONObject clientObj = defaultClient.getJSONObject(client);
		
        innerTubeContext = clientObj.getJSONObject("innerTubeContext");
        requireJsPlayer = clientObj.getBoolean("requireJsPlayer");
        requirePoToken = clientObj.getBoolean("requirePoToken");
        header = clientObj.getJSONObject("header");
        apiKey = clientObj.getString("apiKey");
        this.usePoToken = usePoToken;
        
        JSONObject tokenData = InnerTubeUtils.loadCachedTokens(usePoToken, allowCache);
        if (tokenData != null) {
            accessVisitorData = tokenData.optString("visitorData");
            accessPoToken = tokenData.optString("poToken");
        }
	}
	
    public InnerTube(String client) throws JSONException {
        this(client, false, false);
    }
    
    public InnerTube(String client, boolean usePoToken) throws JSONException {
        this(client, usePoToken, false);
    }
 
    private String getBaseUrl() {
    	return "https://www.youtube.com/youtubei/v1";
    }
    
    
}


