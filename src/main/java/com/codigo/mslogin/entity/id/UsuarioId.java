package com.codigo.mslogin.entity.id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioId {
  private Long codEmpresa;
  private Long codUsuario;
}
