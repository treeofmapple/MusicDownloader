package javatube;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class InnerTube{
    private static JSONObject innerTubeContext;
    private static boolean requireJsPlayer;
    private static boolean requirePoToken;
    private static JSONObject header;
    private static String apiKey;

    private final boolean usePoToken;
    private String accessPoToken;
    private String accessVisitorData;

    private JSONObject defaultClient = new JSONObject();

    public InnerTube(String client, boolean usePoToken) throws JSONException {
        this(client, usePoToken, false);
    }
    
    public InnerTube(String client) throws JSONException {
        this(client, false, false);
    }
    
    public InnerTube(String client, boolean usePoToken, boolean allowCache) throws JSONException {

        innerTubeContext = defaultClient.getJSONObject(client).getJSONObject("innerTubeContext");
        requireJsPlayer = defaultClient.getJSONObject(client).getBoolean("requireJsPlayer");
        requirePoToken = defaultClient.getJSONObject(client).getBoolean("requirePoToken");
        header = defaultClient.getJSONObject(client).getJSONObject("header");

        apiKey = defaultClient.getJSONObject(client).getString("apiKey");

        this.usePoToken = usePoToken;

        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            Path path = Paths.get(tempDir, "tokens.json");

            if (usePoToken && allowCache && Files.exists(path)) {
                String content = new String(Files.readAllBytes(path));
                JSONObject jsonObject = new JSONObject(content);
                accessVisitorData = jsonObject.getString("visitorData");
                accessPoToken = jsonObject.getString("poToken");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getInnerTubeContext() throws JSONException {
        return innerTubeContext;
    }
    
    public void updateInnerTubeContext(JSONObject innerTubeContext, JSONObject extraInfo) throws JSONException {
        for (Iterator<String> it = extraInfo.keys(); it.hasNext(); ) {
            String key = it.next();
            if (innerTubeContext.has(key) && innerTubeContext.get(key) instanceof JSONObject) {
                updateInnerTubeContext(innerTubeContext.getJSONObject(key), extraInfo.getJSONObject(key));
            } else {
                innerTubeContext.put(key, extraInfo.get(key));
            }
        }
    }
    public Map<String, String> getClientHeaders() throws JSONException {
        return getHeaderMap();
    }
    @Deprecated
    public String getClientApiKey() throws JSONException {
        return apiKey;
    }
    public boolean getRequireJsPlayer(){
        return requireJsPlayer;
    }

    public boolean getRequirePoToken(){
        return requirePoToken;
    }

    public String getVisitorData(){
        return accessVisitorData;
    }

    public String getPoToken(){
        return accessPoToken;
    }

    private String getBaseUrl(){
        return "https://www.youtube.com/youtubei/v1";
    }

    private String getBaseParam(){
        return "{prettyPrint: \"false\"}";
    }

    private String[] defaultPoTokenVerifier(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("You can use the tool: https://github.com/YunzheZJU/youtube-po-token-generator, to get the token");
        System.out.print("Enter with your visitorData: ");
        String visitorData = scanner.nextLine();
        System.out.print("Enter with your PoToken: ");
        String poToken = scanner.nextLine();
        return new String[]{visitorData, poToken};
    }

    public void cacheTokens() throws JSONException {
        if (usePoToken){
            JSONObject data = new JSONObject(
                    "{" +
                                "\"visitorData\": \"" + accessVisitorData + "\"," +
                                "\"poToken\": \"" + accessPoToken + "\"" +
                            "}"
            );

            try {
                String tempDir = System.getProperty("java.io.tmpdir");
                Path path = Paths.get(tempDir, "tokens.json");
                Files.write(path, data.toString(4).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertVisitorData(String visitorData) throws JSONException {
        JSONObject context = new JSONObject(
                "{" +
                            "\"context\": {" +
                                "\"client\": {" +
                                    "\"visitorData\": \"" + visitorData + "\"" +
                                "}"+
                            "}" +
                        "}"
        );
        updateInnerTubeContext(innerTubeContext, context);
    }

    public void insetPoToken() throws JSONException {
        JSONObject context = new JSONObject(
                "{" +
                            "\"context\": {" +
                                "\"client\": {" +
                                    "\"visitorData\": \"" + accessVisitorData + "\"" +
                                "}"+
                            "}," +
                            "\"serviceIntegrityDimensions\": {" +
                                "\"poToken\": \"" + accessPoToken + "\"" +
                            "}" +
                        "}"
        );
        updateInnerTubeContext(innerTubeContext, context);
    }

    public void insetPoToken(String poToken, String visitorData) throws JSONException {
        JSONObject context = new JSONObject(
                "{" +
                            "\"context\": {" +
                                "\"client\": {" +
                                    "\"visitorData\": \"" + visitorData + "\"" +
                                "}"+
                            "}," +
                            "\"serviceIntegrityDimensions\": {" +
                                "\"poToken\": \"" + poToken + "\"" +
                            "}" +
                        "}"
        );
        updateInnerTubeContext(innerTubeContext, context);
    }

    public void fetchPoToken() throws JSONException {
        String[] token = defaultPoTokenVerifier();
        accessVisitorData = token[0];
        accessPoToken = token[1];
        cacheTokens();
        insetPoToken();
    }

    private String urlEncode(JSONObject json) throws JSONException, UnsupportedEncodingException {
        StringBuilder query = new StringBuilder();
        for (Iterator<String> it = json.keys(); it.hasNext(); ) {
            String key = it.next();
            String value = json.getString(key);
            query.append(URLEncoder.encode(key, StandardCharsets.UTF_8.name()));
            query.append("=");
            query.append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
            query.append("&");
        }
        if (query.length() != 0) {
            query.setLength(query.length() - 1);
        }
        return query.toString();
    }

    private Map<String, String> getHeaderMap() throws JSONException {
        HashMap<String, String> headers = new HashMap<>();
        Iterator<String> keys = header.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = header.getString(key);
            headers.put(key, value);
        }
        return headers;
    }

    private JSONObject callApi(String endpoint, JSONObject query) throws Exception {

        String endpointUrl = endpoint + "?" + urlEncode(query);

        if(usePoToken){
            if(accessPoToken != null){
                insetPoToken();
            }else {
                fetchPoToken();
            }
        }

        ByteArrayOutputStream response = Request.post(endpointUrl, getInnerTubeContext().toString(), getHeaderMap());
        return new JSONObject(response.toString());
    }

    public JSONObject player(String videoId) throws Exception {
        String endpoint = getBaseUrl() + "/player";
        JSONObject query = new JSONObject(getBaseParam());
        JSONObject context = new JSONObject("{videoId: " + videoId + ", " + "contentCheckOk: \"true\"" + "}");
        updateInnerTubeContext(getInnerTubeContext(), context);
        return callApi(endpoint, query);
    }

    public JSONObject browse(JSONObject data) throws Exception {
        String endpoint = getBaseUrl() + "/browse";
        JSONObject query = new JSONObject(getBaseParam());
        updateInnerTubeContext(getInnerTubeContext(), data);
        return callApi(endpoint, query);
    }

    public JSONObject search(String searchQuery, JSONObject data) throws Exception {
        String endpoint = getBaseUrl() + "/search";
        JSONObject query = new JSONObject(getBaseParam());
        JSONObject contextQuery = new JSONObject("{query: " + searchQuery + "}");
        updateInnerTubeContext(getInnerTubeContext(), contextQuery);

        if (data.length() > 0){
            updateInnerTubeContext(getInnerTubeContext(), data);
        }
        return callApi(endpoint, query);
    }
}