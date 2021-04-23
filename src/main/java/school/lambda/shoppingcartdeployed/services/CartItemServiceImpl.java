package school.lambda.shoppingcartdeployed.services;

import school.lambda.shoppingcartdeployed.exceptions.ResourceNotFoundException;
import school.lambda.shoppingcartdeployed.models.CartItem;
import school.lambda.shoppingcartdeployed.models.CartItemId;
import school.lambda.shoppingcartdeployed.models.Product;
import school.lambda.shoppingcartdeployed.models.User;
import school.lambda.shoppingcartdeployed.repository.CartItemRepository;
import school.lambda.shoppingcartdeployed.repository.ProductRepository;
import school.lambda.shoppingcartdeployed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "cartitemService")
public class CartItemServiceImpl implements CartItemService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private ProductRepository prodrepos;

    @Autowired
    private CartItemRepository cartitemrepos;

    @Override
    public CartItem addToCart(
        long userid,
        long productid,
        String comment)
    {
        User workingUser = userrepos.findById(userid)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));

        Product workingProduct = prodrepos.findById(productid)
            .orElseThrow(() -> new ResourceNotFoundException("Product id " + productid + " not found!"));

        CartItem workingCartItem = cartitemrepos.findById(new CartItemId(userid,
            productid))
            .orElse(new CartItem(workingUser,
                workingProduct,
                0,
                comment));

        workingCartItem.setQuantity(workingCartItem.getQuantity() + 1);
        if (comment != null)
        {
            workingCartItem.setComments(comment);
        }

        return cartitemrepos.save(workingCartItem);
    }

    @Override
    public CartItem removeFromCart(
        long userid,
        long productid,
        String comment)
    {
        User workingUser = userrepos.findById(userid)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));

        Product workingProduct = prodrepos.findById(productid)
            .orElseThrow(() -> new ResourceNotFoundException("Product id " + productid + " not found!"));

        CartItem workingCartItem = cartitemrepos.findById(new CartItemId(userid,
            productid))
            .orElseThrow(() -> new ResourceNotFoundException("Product " + productid + " not found in User's Cart"));

        workingCartItem.setQuantity(workingCartItem.getQuantity() - 1);
        if (comment != null)
        {
            workingCartItem.setComments(comment);
        }

        if (workingCartItem.getQuantity() <= 0)
        {
            cartitemrepos.deleteById(new CartItemId(userid,
                productid));
            return null;
        } else
        {
            return cartitemrepos.save(workingCartItem);
        }
    }
}
