package jar.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jar.model.Account;

public class AccountDAO implements IDAO<Account> {
    private List<Account> cuentas = Arrays.asList(new Account(1, "Master", "Nombre", "Apellido"),
            new Account(2, "NumberTwo", "NombreTwo", "ApellidoTwo"));

    @Override
    public List<Account> getAll() {
        return cuentas;
    }

    @Override
    public Optional<Account> get(int id) {
        return Optional.of((cuentas.stream().filter(c -> c.getId() == id).collect(Collectors.toList())).get(0));
    }
}