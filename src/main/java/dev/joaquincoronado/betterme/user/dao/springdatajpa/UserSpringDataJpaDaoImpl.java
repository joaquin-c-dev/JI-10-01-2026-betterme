package dev.joaquincoronado.betterme.user.dao.springdatajpa;

import dev.joaquincoronado.betterme.user.dao.IUserDao;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.model.BettermeUserEntity;
import dev.joaquincoronado.betterme.user.model.IBettermeUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository("userSpringDataJpa")
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
        BettermeUserEntity entity = this.mapper.toEntity(user);
        this.userRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public BettermeUser getById(long id) {
        BettermeUserEntity entity = this.userRepository.findById(id).orElse(null);
        return this.mapper.toModel(entity);
    }

    @Override
    public BettermeUser getByEmail(String email) {
        BettermeUserEntity entity = this.userRepository.findByEmail(email);
        return this.mapper.toModel(entity);
    }
}
