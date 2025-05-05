package javatube.utils;

public final class SystemUtils {

	public static String checkPath(String fileName, String savePath) {
		if (savePath.endsWith("/")) {
			return savePath + fileName;
		} else {
			return savePath + "/" + fileName;
		}
	}
	
}
