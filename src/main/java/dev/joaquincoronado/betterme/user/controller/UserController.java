package dev.joaquincoronado.betterme.user.controller;

import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/v1")
public class UserController {

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
    public BettermeUser updateUser(@RequestBody BettermeUser user){
        return this.userUseCase.updateUseCase(user);
    }

}
