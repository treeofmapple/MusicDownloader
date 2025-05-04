package javatube.captions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javatube.utils.SystemLogger;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CaptionsUtils {

	private final Captions caption;
	
	protected String getXmlCaptions() {
    	HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(caption.getUrl()))
                .header("User-Agent", "Mozilla/5.0")
                .build();
    	try {
    		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    		return response.body().replaceAll("(&#39;)|(&amp;#39;)", "'");
    	} catch(IOException | InterruptedException e) {
    		Thread.currentThread().interrupt();
    		SystemLogger.error("Unable to get captions", e);
    		return null;
    	}
    }
	
	protected String xmlCaptionToStr() {
		try {
			return generateRegex();
		} catch (Exception e) {
			SystemLogger.error("Failed to generate captions", e);
			return "";
		}
	}
	
	private String generateSrtCaptions() throws Exception {
		return getXmlCaptions();
	}
	
	private String decodeString(String decode) throws UnsupportedEncodingException {
		return URLDecoder.decode(decode, StandardCharsets.UTF_8.name());
	}
	
	private String srtTimeFormat(Float d) {
		int totalMilliseconds = Math.round(d * 1000);
        int hours = totalMilliseconds / 3600000;
        int minutes = (totalMilliseconds % 3600000) / 60000;
        int seconds = (totalMilliseconds % 60000) / 1000;
        int milliseconds = totalMilliseconds % 1000;
        
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, milliseconds);
	}

	private String generateRegex() throws Exception {
		String xml = generateSrtCaptions();
		if (xml == null) {
			return "";
		}
		
		StringBuilder segments = new StringBuilder();
		
		String[] pattern = {
                "start=\\\"(.*?)\\\".*?dur=\\\"(.*?)\\\">(.*?)<",
                "t=\\\"(.*?)\\\".*?d=\\\"(.*?)\\\">(.*?)<"
		};

		int i = 1;
		for (String s : pattern) {
			Matcher matcher = Pattern.compile(s).matcher(xml);
			while(matcher.find()) {
				Float start = Float.parseFloat(matcher.group(1));
				Float end = start + Float.parseFloat(matcher.group(2));
				String caption = decodeString(matcher.group(3));
				
				segments.append(i++)
			    .append("\n")
			    .append(srtTimeFormat(start))
			    .append(" --> ")
			    .append(srtTimeFormat(end))
			    .append("\n")
			    .append(caption)
			    .append("\n\n");
			}
		}
		
		return segments.toString();
	}

}
