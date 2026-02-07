package dev.joaquincoronado.betterme.user.model;

import dev.joaquincoronado.betterme.shared.exception.NotFoundException;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BettermeUser {

    @EqualsAndHashCode.Include
    private Long id;
    private String email;
    private String name;
    private String password;
    private Role role;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }
}
