import java.util.ArrayList;
import java.util.List;

public class Dataset {

    //Hard coded Dataset

    public  List<String> generateChallengesList() {
        ArrayList<String> challenges = new ArrayList<>();  // Added <String> generics
        challenges.add("latency issues under high load");
        challenges.add("Security vulnerabilities in outdated dependencies");
        challenges.add("Inconsistent configuration across environments");
        return challenges;
    }

    public  List<String> generateIncidentList(){
        ArrayList<String> incidents = new ArrayList<>();
        incidents.add("Critical system failure");
        incidents.add("User complaint received");
        incidents.add("Monitoring alerts triggered");
        return incidents;
    }
    public  List<String>generateTroubleshootingStepsList(){
        ArrayList<String> troubleshootingSteps= new ArrayList<>();
        troubleshootingSteps.add("Check system logs");
        troubleshootingSteps.add("identify bug");
        troubleshootingSteps.add("restrict access");
        troubleshootingSteps.add("restart affected services");
        return troubleshootingSteps;
    }
}