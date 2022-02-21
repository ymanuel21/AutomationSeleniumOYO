package testCaseYummy.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

	public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream filePath = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
		return objectMapper.readValue(filePath, T);
	}
}
