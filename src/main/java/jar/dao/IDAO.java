package jar.dao;

import java.util.List;
import java.util.Optional;

// Interfaz de todos los DAO
// En realidad un DAO tambien permite guardar y borrar, pero esas creo que habiamos quedado en no hacerlas

// Un DAO es un patron que se usa para asilar la capa de negocio de la capa de persistencia.
public interface IDAO<E> {
    public List<E> getAll(int page);

    public Optional<E> get(int id);

    void save(E e);

    void update(int id, E e);

    void delete(E e);
}