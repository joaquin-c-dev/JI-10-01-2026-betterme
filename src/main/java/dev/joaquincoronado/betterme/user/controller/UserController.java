package dev.joaquincoronado.betterme.user.controller;

import dev.joaquincoronado.betterme.auth.model.BettermeAuth;
import dev.joaquincoronado.betterme.shared.model.RequesterInfo;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController implements IUserController{

    private final UserUseCase userUseCase;

    @PostMapping("/user")
    public BettermeUser createUser(@RequestBody BettermeUser user) {
        return this.userUseCase.creatUseCase(user);
    }

    @GetMapping("/user/{userId}")
    public BettermeUser getById(@PathVariable long userId){
       return this.userUseCase.getUserByIdUseCase(userId);
    }

    @DeleteMapping("/user")
    public BettermeUser deleteUser(@RequestParam long userId){
        return this.userUseCase.deleteUseCase(userId);
    }

    @PutMapping("/user")
    public BettermeUser updateUser(
        @CurrentSecurityContext(expression = "authentication") BettermeAuth bettermeAuth,
        @RequestBody BettermeUser user
    ){
        RequesterInfo requesterInfo = bettermeAuth.getRequesterInfo();
        return this.userUseCase.updateUseCase(requesterInfo, user);
    }

}
