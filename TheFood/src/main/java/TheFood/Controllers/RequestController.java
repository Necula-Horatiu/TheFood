package TheFood.Controllers;

import TheFood.Models.PredictionModel;
import TheFood.ParseClass;
import TheFood.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@RequestMapping("api/request")
@RestController
public class RequestController {

    private static HttpURLConnection connection;

    private static ParseClass parseClass;

    private static FoodService foodService;

    public RequestController(ParseClass parseClass, FoodService foodService) {
        this.parseClass = parseClass;
        this.foodService = foodService;
    }

    @PostMapping
    public ArrayList<PredictionModel> postImage(@ModelAttribute MultipartFile file) throws IOException {

        String url = "https://southcentralus.api.cognitive.microsoft.com/customvision/v3.0/Prediction/2e6377a5-d71d-4372-b27b-f601283a23a3/classify/iterations/Iteration1/image";
        byte[] postData = file.getBytes();

        try {
            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Prediction-Key", "f4bdd1b397804766aa463aa0e1ca0b3d");
            connection.setRequestProperty("Content-Type", "application/octet-stream");

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            ArrayList<PredictionModel> predictions = parseClass.getPredictions(content.toString());
            PredictionModel obj = new PredictionModel("", 0.0);
            for (int i = 0; i < predictions.size(); i++) {
                if (obj.getProbability() < predictions.get(i).getProbability()) {
                    obj = predictions.get(i);
                }
            }
            System.out.println(foodService.getFoodByName(obj.getTagName()).getIngredients());
            return parseClass.getPredictions(content.toString());
        } finally {
            connection.disconnect();
        }
    }
}
