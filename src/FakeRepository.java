import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class FakeRepository<K, V extends Entity<K>> {
    private final Map<K, V> repository;

    public FakeRepository() {
        this.repository = new HashMap<>();
    }

    public void save(V value) {
        if(repository.containsKey(value.getKey())) throw new EntityAlreadyExistsException("This entity already exists in the system");
        repository.put(value.getKey(), value);
    }

    public void update(V value) {
        if(!repository.containsKey(value.getKey())) throw new NoSuchElementException("This entity does not exist in the system");
        repository.put(value.getKey(), value);
    }

    public void delete(V value) {
        if(!repository.containsKey(value.getKey())) throw new NoSuchElementException("This entity does not exist in the system");
        repository.remove(value.getKey());
    }
}
