@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository repo;
    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemServiceImpl(
            CartItemRepository repo,
            CartRepository cartRepo,
            ProductRepository productRepo) {

        this.repo = repo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public CartItem addItem(Long cartId, Long productId, Integer quantity) {
        CartItem item = new CartItem();
        item.setCart(cartRepo.findById(cartId).orElseThrow());
        item.setProduct(productRepo.findById(productId).orElseThrow());
        item.setQuantity(quantity);
        return repo.save(item);
    }
}
