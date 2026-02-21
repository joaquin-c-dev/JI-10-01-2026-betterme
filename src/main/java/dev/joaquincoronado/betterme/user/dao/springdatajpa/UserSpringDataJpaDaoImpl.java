package dev.joaquincoronado.betterme.user.dao.springdatajpa;

import dev.joaquincoronado.betterme.user.dao.IUserDao;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.model.BettermeUserEntity;
import dev.joaquincoronado.betterme.user.model.IBettermeUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
public class UserSpringDataJpaDaoImpl implements IUserDao {

    private final IBettermeUserRepository userRepository;
    private final IBettermeUserMapper mapper;

    @Override
    public void create(BettermeUser user) {
        BettermeUserEntity entity = this.mapper.toEntity(user);
        this.userRepository.save(entity);
        user.setId(entity.getId());
    }

    @Override
    public void update(BettermeUser user) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public BettermeUser getById(long id) {
        return null;
    }

    @Override
    public BettermeUser getByEmail(String email) {
        return null;
    }
}
