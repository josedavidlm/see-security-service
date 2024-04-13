package com.codigo.mslogin.entity;

import com.codigo.mslogin.entity.id.UsuarioId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario", schema = "dse")
@Getter
@Setter
@RequiredArgsConstructor
@IdClass(UsuarioId.class)
public class Usuario extends  AuditoriaEntidadEntity implements UserDetails {

    @Id
    @Column(name = "codempresa")
    private Long codEmpresa;

    @Id
    @Column(name = "codusuario")
    private Long codUsuario;

    @Column(name = "loginusuario")
    private String loginUsuario;

    @Column(name = "nomusuario")
    private String nomUsuario;

    @Column(name = "password")
    private String password;

    @Column(name = "tipdid")
    private Long tipdid;

    @Column(name = "docide")
    private String docide;

    @Column(name = "codest")
    private Long codEst;

    @Column(name = "activo")
    private Boolean activo;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return loginUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
