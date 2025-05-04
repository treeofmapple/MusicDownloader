package javatube.captions;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

public abstract class Captions {

	@Getter
	private final String url;
	
	@Getter
	private final String code;
	
	@Getter
	private final String name;
	
	public Captions(JSONObject captionTrack) throws JSONException {
		url = captionTrack.getString("baseUrl");
		String vssId = captionTrack.getString("vssId");
		code = vssId.startsWith(".") ? vssId.replace(".", "") : vssId;
		JSONObject nameContent = captionTrack.getJSONObject("name");
		name = nameContent.has("simpleText") 
				? nameContent.getString("simpleText") 
						: nameContent
						.getJSONArray(vssId)
						.getJSONObject(0)
						.getString("text");
	}
	
    @Override
    public String toString(){
        return "<Caption lang=\"" + name + "\" code=\"" + code + "\">";
    }
	
}
