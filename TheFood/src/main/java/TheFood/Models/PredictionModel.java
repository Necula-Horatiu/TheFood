package TheFood.Models;

public class PredictionModel {

    private Double probability;

    private String tagName;

    public PredictionModel() { }

    public PredictionModel(String tagName, Double probability) {
        this.tagName = tagName;
        this.probability = probability;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
