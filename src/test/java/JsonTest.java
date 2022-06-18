import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    ClassLoader classLoader = JsonTest.class.getClassLoader();
   @DisplayName("json with Jackson library test")
    @Test
    void jsonTest() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream));

        assertThat(jsonNode.get("pid").asInt()).isEqualTo(1047);
        assertThat(jsonNode.get("experience").asText()).isEqualTo("5 years");
        assertThat(jsonNode.get("isGoodMaster").asBoolean()).isEqualTo(true);
        assertThat(jsonNode.findValue("issuedBy").asText()).isEqualTo("SFV Motor");


    }
}
