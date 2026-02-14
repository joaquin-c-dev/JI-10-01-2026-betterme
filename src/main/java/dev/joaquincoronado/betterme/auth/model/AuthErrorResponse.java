package dev.joaquincoronado.betterme.auth.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorResponse {
    @Builder.Default
    private String type = "about:blank";
    private String title;
    private Integer status;
    private String detail;
}
