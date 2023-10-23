/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.Repositorios;

import com.noticiasegg.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface PeriodistaRepositorio extends JpaRepository<Periodista, String> {

    public Periodista findBynombreUsuario(String nombreUsuario);

    public boolean existsByNombreUsuario(String nombreUsuario);
    


}