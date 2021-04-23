package school.lambda.shoppingcartdeployed.services;

import school.lambda.shoppingcartdeployed.models.CartItem;

public interface CartItemService
{
    CartItem addToCart(
        long userid,
        long productid,
        String comment);

    CartItem removeFromCart(
        long userid,
        long productid,
        String comment);
}
