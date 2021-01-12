package jar.dao;

import java.util.List;
import java.util.Optional;

// Interfaz de todos los DAO
// En realidad un DAO tambien permite guardar y borrar, pero esas creo que habiamos quedado en no hacerlas

// Un DAO es un patron que se usa para asilar la capa de negocio de la capa de persistencia.
public interface IDAO<K, V> {
    public List<V> getAll(boolean startOver);

    public Optional<V> get(K id);

    void save(V e);

    void update(K id, V e);

    void delete(V e);
}