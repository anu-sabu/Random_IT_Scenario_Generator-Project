import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ResponseGenerator {

    private static final Random RANDOM = new Random();

    public static String getRandomItem(List<String> list){
        return list.get(RANDOM.nextInt(list.size()));
    }

    public String generateScenario(String technology, String role, String environment) {
        // Create response object
        Map<String, Object> inputs = new HashMap<>();
        inputs.put("technology", technology);
        inputs.put("role", role);
        inputs.put("environment", environment);

        Dataset dataset = new Dataset();

        //create scenario object
        Map<String, String> scenario = new HashMap<>();
        scenario.put("challenge", getRandomItem(dataset.generateChallengesList()));
        scenario.put("incident", getRandomItem(dataset.generateIncidentList()));
        scenario.put("troubleshooting", getRandomItem(dataset.generateTroubleshootingStepsList()));

        return createOutputJson(inputs, scenario);
    }

    //Manual Json Creation
    public static String createOutputJson(Map<String, Object> inputs, Map<String, String> scenario){
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("   \"technology\":\"").append(inputs.get("technology")).append("\",\n");
        json.append("   \"role\":\"").append(inputs.get("role")).append("\",\n");
        json.append("   \"environment\":\"").append(inputs.get("environment")).append("\",\n");
        json.append("   \"scenario\":{\n");
        json.append("       \"challenge\":\"").append(scenario.get("challenge")).append("\",\n");
        json.append("       \"incident\":\"").append(scenario.get("incident")).append("\",\n");
        json.append("       \"troubleshootingStep\":\"").append(scenario.get("troubleshooting")).append("\"\n");
        json.append("   }\n");
        json.append("}");

        return json.toString();
    }
}