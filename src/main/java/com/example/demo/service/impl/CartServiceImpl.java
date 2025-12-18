@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repo;

    public CartServiceImpl(CartRepository repo) {
        this.repo = repo;
    }

    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repo.save(cart);
    }

    public Cart getCart(Long cartId) {
        return repo.findById(cartId).orElseThrow();
    }
}
