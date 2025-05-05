package javatube.captions;

import java.io.FileWriter;

import javatube.exceptions.DownloadFailedException;
import javatube.utils.SystemLogger;
import javatube.utils.SystemUtils;

public class CaptionsOperations {

	public void download(String fileName, String savePath) {
		String path = SystemUtils.checkPath(fileName, savePath);
		String content;

		try {
			if (fileName.endsWith(".srt")) {
				content = CaptionsUtils.xmlCaptionToStr();
			} else {
				content = CaptionsUtils.getXmlCaptions();
			}
			if (content == null || content.isBlank()) {
				throw new DownloadFailedException("No content to write.");
			}
			try (FileWriter writer = new FileWriter(path)) {
				writer.write(content);
			}

		} catch (Exception e) {
			SystemLogger.error("Failed to download captions to" + path, e);
			throw new DownloadFailedException("Failed to download captions", e);
		}
	}
	
}
