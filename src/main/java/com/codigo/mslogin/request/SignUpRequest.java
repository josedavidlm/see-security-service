package com.codigo.mslogin.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SignUpRequest {

    private Long codEmpresa;
    @JsonIgnore
    private Long codUsuario;
    private String loginUsuario;
    private String nomUsuario;
    private String password;
    private Long tipdid;
    private String docide;
    @JsonIgnore
    private LocalDateTime fecCreacion;
    @JsonIgnore
    private Long codUsuarioCreacion;
    @JsonIgnore
    private  String nomTerCreacion;
    @JsonIgnore
    private LocalDateTime fecModificacion;
    @JsonIgnore
    private Long codUsuarioModificacion;
    @JsonIgnore
    private  String nomTerModificacion;
    @JsonIgnore
    private LocalDateTime fecEliminacion;
    @JsonIgnore
    private Long codUsuarioEliminacion;
    @JsonIgnore
    private  String nomTerEliminacion;
    @JsonIgnore
    private Long codEst;

}
