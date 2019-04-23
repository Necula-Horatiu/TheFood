package TheFood.Models;

public class PredictionModel {

    private String probability;

    private String tagName;

    public PredictionModel() {
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
