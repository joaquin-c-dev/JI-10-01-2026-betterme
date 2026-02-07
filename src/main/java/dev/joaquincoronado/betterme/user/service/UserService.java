package dev.joaquincoronado.betterme.user.service;

import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import dev.joaquincoronado.betterme.user.dao.IUserDao;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserDao userDao;

    public BettermeUser getByEmail(String email){
        return this.userDao.getByEmail(email);
    }

    public void create(BettermeUser user){
        user.setCreatedAt(LocalDateTime.now());
        this.userDao.create(user);
    }

    public BettermeUser getById(long id){
        return this.userDao.getById(id);
    }

    public void update(BettermeUser user){
        user.setUpdatedAt(LocalDateTime.now());
        this.userDao.update(user);
    }

    public void delete(long id){
        this.userDao.delete(id);
    }

    public void mergeUserToUpdate(BettermeUser current, BettermeUser updated){
        if(updated.getEmail() != null){
            current.setEmail(updated.getEmail());
        }

        if(updated.getName() != null){
            current.setName(updated.getName());
        }

        if(updated.getPassword() != null){
            current.setPassword(updated.getPassword());
        }

        if(updated.getRole() != null){
            current.setRole(updated.getRole());
        }

        if(updated.getActive() != null){
            current.setActive(updated.getActive());
        }
    }
}
