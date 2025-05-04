package javatube.utils;

public class SystemUtils {

	public String checkPath(String fileName, String savePath) {
		if (savePath.endsWith("/")) {
			return savePath + fileName;
		} else {
			return savePath + "/" + fileName;
		}
	}
	
}
