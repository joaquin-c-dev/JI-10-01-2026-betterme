package dev.joaquincoronado.betterme.auth.model;

import dev.joaquincoronado.betterme.shared.model.RequesterInfo;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class BettermeAuth extends UsernamePasswordAuthenticationToken {

    private final RequesterInfo requesterInfo;

    public BettermeAuth(
        Object principal,
        @Nullable Object credentials,
        Collection<? extends GrantedAuthority> authorities,
        RequesterInfo requesterInfo
    ) {
        super(principal, credentials, authorities);
        this.requesterInfo = requesterInfo;
    }
}
