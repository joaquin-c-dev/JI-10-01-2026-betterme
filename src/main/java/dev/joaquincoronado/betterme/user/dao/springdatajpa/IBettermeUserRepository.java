package dev.joaquincoronado.betterme.user.dao.springdatajpa;

import dev.joaquincoronado.betterme.user.model.BettermeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBettermeUserRepository extends JpaRepository<BettermeUserEntity, Long> {

}
