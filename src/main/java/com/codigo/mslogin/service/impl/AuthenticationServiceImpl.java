package com.codigo.mslogin.service.impl;

import com.codigo.mslogin.entity.Role;
import com.codigo.mslogin.entity.Usuario;
import com.codigo.mslogin.repository.UsuarioRepository;
import com.codigo.mslogin.request.SignInRequest;
import com.codigo.mslogin.request.SignUpRequest;
import com.codigo.mslogin.response.AuthenticationResponse;
import com.codigo.mslogin.service.AuthenticationService;
import com.codigo.mslogin.service.JWTService;
import com.codigo.mslogin.util.Constantes;
import com.codigo.mslogin.util.EstadoRegistro;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public Usuario signUpUser(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setCodEmpresa(signUpRequest.getCodEmpresa());
        usuario.setCodUsuario(usuarioRepository.obtenerUsuarioId(signUpRequest.getCodEmpresa()));
        usuario.setNomUsuario(signUpRequest.getNomUsuario());
        usuario.setLoginUsuario(signUpRequest.getLoginUsuario());
        usuario.setTipdid(signUpRequest.getTipdid());
        usuario.setDocide(signUpRequest.getDocide());
        usuario.setFecCreacion(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setCodEst(EstadoRegistro.REGISTRADO.getValue());
        usuario.setCodUsuarioCreacion(1L);
        usuario.setNomTerCreacion(Constantes.IP_TERMINAL);
        usuario.setRole(Role.USER);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {
        Usuario usuario = new Usuario();
        usuario.setCodEmpresa(signUpRequest.getCodEmpresa());
        usuario.setCodUsuario(usuarioRepository.obtenerUsuarioId(signUpRequest.getCodEmpresa()));
        usuario.setNomUsuario(signUpRequest.getNomUsuario());
        usuario.setLoginUsuario(signUpRequest.getLoginUsuario());
        usuario.setTipdid(signUpRequest.getTipdid());
        usuario.setDocide(signUpRequest.getDocide());
        usuario.setFecCreacion(LocalDateTime.now());
        usuario.setActivo(true);
        usuario.setCodEst(EstadoRegistro.REGISTRADO.getValue());
        usuario.setCodUsuarioCreacion(1L);
        usuario.setNomTerCreacion(Constantes.IP_TERMINAL);
        usuario.setRole(Role.ADMIN);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    //MEOTODO DE LOGIN
    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getLoginUsuario(),signInRequest.getPassword()));
        var user = usuarioRepository.findByLoginUsuario(signInRequest.getLoginUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Login no valido"));

        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
}
