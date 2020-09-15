package jar.business;

import java.util.List;
import java.util.Optional;

public interface IBusiness<E> {

    public List<E> getAll();

    public Optional<E> get(int id);
}