package dev.joaquincoronado.betterme.user.usecase;

import dev.joaquincoronado.betterme.shared.exception.BadRequestException;
import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserService userService;

    public BettermeUser creatUseCase(BettermeUser user) {
        //validar que el email no sea nulo
        if(user.getEmail() == null) throw new BadRequestException("email is required");

        //validar que no se repita el email
        BettermeUser currentUser = this.userService.getByEmail(user.getEmail());
        //mandar una excepción si se repite el email
        if(currentUser != null) throw new BadRequestException("email already exists");

        //crear el usuario
        this.userService.create(user);

        //retornar el usuario creado
        return user;
    }

    public BettermeUser getUserByIdUseCase(long userId){
        BettermeUser user = this.userService.getById(userId);
        if(user == null) throw new NotFoundException("user not found");
        return user;
    }

    public BettermeUser deleteUseCase(long userId){
        BettermeUser user = this.userService.getById(userId);
        if(user == null) throw new NotFoundException("user not found");
        this.userService.delete(userId);
        return user;
    }

    public BettermeUser updateUseCase(BettermeUser user){
        if(user.getId() == null) throw new BadRequestException("id is required");

        BettermeUser currentUser = this.userService.getById(user.getId());
        if(currentUser == null) throw new NotFoundException("user not found");

        this.userService.mergeUserToUpdate(currentUser, user);
        this.userService.update(currentUser);
        return currentUser;
    }



}
