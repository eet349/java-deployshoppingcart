package school.lambda.shoppingcartdeployed.repository;

import school.lambda.shoppingcartdeployed.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>
{
}
