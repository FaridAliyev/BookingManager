package DAOs;

import java.util.List;
import java.util.Optional;


public interface DAO<T> {
    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    boolean delete(T t);
}


