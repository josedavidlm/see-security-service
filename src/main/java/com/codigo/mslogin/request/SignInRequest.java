package com.codigo.mslogin.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String loginUsuario;
    private String password;
}
