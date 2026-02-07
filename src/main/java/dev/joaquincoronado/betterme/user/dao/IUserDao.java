package dev.joaquincoronado.betterme.user.dao;

import dev.joaquincoronado.betterme.user.model.BettermeUser;
import org.springframework.stereotype.Repository;

public interface IUserDao {
    void create(BettermeUser user);
    void update(BettermeUser user);
    void delete(long id);
    BettermeUser getById(long id);
    BettermeUser getByEmail(String email);
}
