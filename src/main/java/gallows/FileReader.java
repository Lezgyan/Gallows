package gallows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gallows.exceptions.FileReadException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class FileReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, List<Word>> read(String name) {
        try (InputStream a = new FileInputStream(name)) {
            return MAPPER.readValue(a, new TypeReference<>() {});
        } catch (Exception e) {
            throw new FileReadException("File with name %s is not read".formatted(name), e);
        }
    }

}
