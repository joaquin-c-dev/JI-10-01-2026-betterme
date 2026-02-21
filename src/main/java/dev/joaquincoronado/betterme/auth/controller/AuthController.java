package dev.joaquincoronado.betterme.auth.controller;

import dev.joaquincoronado.betterme.auth.model.AuthRequest;
import dev.joaquincoronado.betterme.auth.model.AuthResponse;
import dev.joaquincoronado.betterme.auth.service.JWTService;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import dev.joaquincoronado.betterme.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){

        this.authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
            )
        );

        BettermeUser authenticatedUser = this.userService.getByEmail(authRequest.getEmail());
        if(authenticatedUser == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");

        String token = this.jwtService.generateToken(authenticatedUser);

        AuthResponse response = AuthResponse.builder().accessToken(token).build();
        return ResponseEntity.ok(response);
    }

}
