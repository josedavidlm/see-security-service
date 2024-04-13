package com.codigo.mslogin.repository;

import com.codigo.mslogin.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByLoginUsuario(String email);

    @Query(value = """
        SELECT COALESCE(Max(codusuario),0)+1
        FROM dse.usuario
        WHERE codempresa = :codEmpresa
      """, nativeQuery = true)
    Long obtenerUsuarioId(Long codEmpresa);
}
