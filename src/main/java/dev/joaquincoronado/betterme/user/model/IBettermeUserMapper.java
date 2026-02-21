package dev.joaquincoronado.betterme.user.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBettermeUserMapper {

    BettermeUser toModel(BettermeUserEntity entity);

    BettermeUserEntity toEntity(BettermeUser model);
}
