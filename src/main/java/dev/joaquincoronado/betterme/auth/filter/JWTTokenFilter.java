package dev.joaquincoronado.betterme.auth.filter;

import dev.joaquincoronado.betterme.auth.service.JWTService;
import dev.joaquincoronado.betterme.user.model.BettermeUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(!this.hasAuthorizationBearer(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = this.getAccessToken(request);

        //validate token an get user from token
        BettermeUser user = this.jwtService.getUserFromToken(token);

        this.setAuthenticationContext(user, request);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request){
        return request.getHeader("Authorization").split(" ")[1];
    }

    private void setAuthenticationContext(BettermeUser user, HttpServletRequest request){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        UsernamePasswordAuthenticationToken authentication
            = new UsernamePasswordAuthenticationToken(user, null, authorities);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
