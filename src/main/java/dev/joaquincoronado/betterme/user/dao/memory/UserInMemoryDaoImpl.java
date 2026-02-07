package dev.joaquincoronado.betterme.user.dao.memory;

import dev.joaquincoronado.betterme.user.dao.IUserDao;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserInMemoryDaoImpl implements IUserDao {

    @Override
    public void create(BettermeUser user) {
        user.setId(new Date().getTime());
        InMemoryUsers.users.add(user);
    }

    @Override
    public void update(BettermeUser user) {
        InMemoryUsers.users.set(InMemoryUsers.users.indexOf(user), user);
    }

    @Override
    public void delete(long id) {
        InMemoryUsers.users.removeIf(user -> user.getId() == id);
    }

    @Override
    public BettermeUser getById(long id) {
        return InMemoryUsers.users.stream()
            .filter( user ->  user.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public BettermeUser getByEmail(String email) {
        return InMemoryUsers.users.stream()
            .filter( user ->  user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
}
