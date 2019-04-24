package TheFood.Services;

import TheFood.Models.FoodModel;
import TheFood.Repositories.FoodRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public void addFood(FoodModel foodModel) {
        foodModel.set_id(new ObjectId());
        foodRepository.save(foodModel);
    }

    public FoodModel getFoodByName(String name) {
        return foodRepository.findByName(name);
    }
}
