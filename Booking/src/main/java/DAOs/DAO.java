package DAOs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface DAO<T> {
    Optional<T> get(UUID id);
    List<T> getAll();
    void save(T t);
    boolean delete(T t);
    boolean delete(UUID id);
}


