@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/user/{userId}")
    public Cart getByUser(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }
}
