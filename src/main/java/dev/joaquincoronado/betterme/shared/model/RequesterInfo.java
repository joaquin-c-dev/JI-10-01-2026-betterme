package dev.joaquincoronado.betterme.shared.model;


import dev.joaquincoronado.betterme.user.model.BettermeUser;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequesterInfo {
    private Long id;
    private String email;
    private BettermeUser.Role role;
}
