package javatube.captions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CaptionQuery {
	Map<String, Captions> langCode = new HashMap<>();
	
	public CaptionQuery(ArrayList<Captions> caption) {
		for(Captions code : caption) {
			langCode.put(code.getCode(), code);
		}
	}
	
	public Captions getByCode(String code) {
		return langCode.get(code);
	}
	
	@Override
	public String toString() {
		return langCode.toString();
	}
	
}
