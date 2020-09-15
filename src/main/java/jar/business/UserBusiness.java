package jar.business;

import java.util.List;
import java.util.Optional;

import jar.dao.UserDAO;
import jar.model.User;

// ! Aca es donde ponemos la logica para traducir las clases del google a la nuestra (la de modelo)

public class UserBusiness implements IBusiness<User> {
    private UserDAO udao = new UserDAO();

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> get(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}