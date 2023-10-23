/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.Repositorios;

import com.noticiasegg.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    //Query muy muy importante para el login!!!!

    /**
     *
     * @param nombreUsuario
     * @return
     */
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario buscarPorUser(@Param("nombreUsuario") String nombreUsuario);

    /**
     *
     * @return
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'USER'")
    List<Usuario> buscarPorRolUser();

    public boolean existsByNombreUsuario(String nombreUsuario);

    @Query("SELECT u FROM Usuario u WHERE u.rol = 'PERIODISTA'")
    List<Usuario> buscarPorRolPeriodista();

}