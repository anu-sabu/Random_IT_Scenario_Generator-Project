
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseGeneratorTest {

    @Test
    public void testGenerateScenario_ShouldReturnJsonOutput() {
        ResponseGenerator service = new ResponseGenerator();
        String result = service.generateScenario("DevOps", "Software Engineer", "Cloud Infrastructure");

        assertNotNull(result);
        assertTrue(result.contains("{"));
        assertTrue(result.contains("}"));
    }

    @Test
    public void testGenerateScenario_ShouldContainAllInputs() {
        ResponseGenerator service = new ResponseGenerator();
        String result = service.generateScenario("Cloud Computing", "System Administrator", "Enterprise Network");

        assertTrue(result.contains("\"technology\":\"Cloud Computing\""));
        assertTrue(result.contains("\"role\":\"System Administrator\""));
        assertTrue(result.contains("\"environment\":\"Enterprise Network\""));
    }

    @Test
    public void testGenerateScenario_ShouldContainScenarioComponents() {
        ResponseGenerator service = new ResponseGenerator();
        String result = service.generateScenario("DevOps", "Software Engineer", "Cloud Infrastructure");

        assertTrue(result.contains("\"scenario\":"));
        assertTrue(result.contains("\"challenge\":"));
        assertTrue(result.contains("\"incident\":"));
        assertTrue(result.contains("\"troubleshootingStep\":"));
    }
}