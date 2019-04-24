package TheFood.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class FoodModel {

    @Id
    private ObjectId _id;

    private String name;

    private String ingredients;

    private String link;

    public FoodModel() { }

    public FoodModel(ObjectId _id, String name, String ingredients, String link) {
        this._id = _id;
        this.name = name;
        this.ingredients = ingredients;
        this.link = link;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
