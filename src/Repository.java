import java.util.Optional;

public interface Repository<V> {
    void save(V value);
    void update(V value);
    void delete(V value);
    Optional<V> findOne(V value);
}
