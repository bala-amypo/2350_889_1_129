@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repo;

    public BundleRuleServiceImpl(BundleRuleRepository repo) {
        this.repo = repo;
    }

    public BundleRule save(BundleRule rule) {
        return repo.save(rule);
    }

    public List<BundleRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
