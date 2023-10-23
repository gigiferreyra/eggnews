/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.Repositorios;

import com.noticiasegg.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Integer> {

    public List<Noticia> findByCreador(String id);

    /**
     *
     * @param sort
     * @return
     */
    /*@Override
    public List<Noticia> findAll(Sort sort);

    public List<Noticia> findByCreador(String id);*/

}