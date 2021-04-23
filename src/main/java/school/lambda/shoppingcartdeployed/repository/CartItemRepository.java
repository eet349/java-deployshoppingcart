package school.lambda.shoppingcartdeployed.repository;

import school.lambda.shoppingcartdeployed.models.CartItem;
import school.lambda.shoppingcartdeployed.models.CartItemId;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemId>
{
}
