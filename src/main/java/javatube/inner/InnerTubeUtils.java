package javatube.inner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

import javatube.utils.SystemLogger;

public final class InnerTubeUtils {

    private InnerTubeUtils() {

    }
	
    public static JSONObject loadDefaultClient() {
        try {
        	String json = new String(Files.readAllBytes(Paths.get("src/main/resources/jsons/innerTube/defaultClient.json")));
            return new JSONObject(json);
        } catch (Exception e) {
        	SystemLogger.error("Unable to load InnerTube client");
        	return null;
        }
    }
	
    public static JSONObject loadCachedTokens(boolean usePoToken, boolean allowCache) {
        try {
            if (usePoToken && allowCache) {
                Path path = Paths.get(System.getProperty("java.io.tmpdir"), "tokens.json");
                if (Files.exists(path)) {
                    String content = new String(Files.readAllBytes(path));
                    return new JSONObject(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
