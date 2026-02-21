package dev.joaquincoronado.betterme.auth.service;

import dev.joaquincoronado.betterme.user.dao.IUserDao;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final IUserDao userDao;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        BettermeUser user = this.userDao.getByEmail(username);

        if(user == null) throw new UsernameNotFoundException("Invalid username or password");

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
