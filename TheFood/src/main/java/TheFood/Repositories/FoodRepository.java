package TheFood.Repositories;

import TheFood.Models.FoodModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<FoodModel, String> {
    FoodModel findByName(String name);
}
