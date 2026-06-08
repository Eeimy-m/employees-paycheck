import java.util.Optional;

public interface Repository<V, K> {
    void save(V value);
    void update(V value);
    void delete(V value);
    Optional<V> findOne(K key);
}
