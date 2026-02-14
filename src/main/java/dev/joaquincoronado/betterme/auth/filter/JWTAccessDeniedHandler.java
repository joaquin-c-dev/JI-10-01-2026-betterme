package dev.joaquincoronado.betterme.auth.filter;

import dev.joaquincoronado.betterme.auth.model.AuthErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        int httpResponse = HttpServletResponse.SC_FORBIDDEN;

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpResponse);

        AuthErrorResponse error = AuthErrorResponse.builder()
            .title("Forbidden")
            .status(httpResponse)
            .detail(accessDeniedException.getMessage())
            .build();

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.flush();
    }
}
