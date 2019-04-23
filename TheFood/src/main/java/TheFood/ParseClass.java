package TheFood;

import TheFood.Models.PredictionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ParseClass {

    public ArrayList<PredictionModel> getPredictions(String content) {
        ArrayList<PredictionModel> mod = new ArrayList<>();
        PredictionModel obj = new PredictionModel();

        int ok = 10;
        String[] arrOfStr = content.split(",");
        for (String a : arrOfStr) {
            String[] ab = a.split(":");
            for (String ac : ab) {
                String[] ad = ac.split("\"");
                for (String aa : ad) {
                    if (aa.equals("probability")) {
                        ok = 1;
                    }
                    if (ok == 2) {
                        if (aa.charAt(0) == '1') {
                            obj.setProbability("0");
                        } else if (aa.charAt(0) == '0') {
                            String upToNCharacters = aa.substring(0, 4);
                            obj.setProbability(upToNCharacters);
                        }
                        ok = 10;
                    }
                    if (aa.equals("tagName")) {
                        ok = 3;
                    }
                    if (ok == 5) {
                        obj.setTagName(aa);
                        mod.add(obj);
                        obj = new PredictionModel();
                        ok = 0;
                    }
                    ok++;
                }
            }
        }

        return mod;
    }
}
