/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.servicios;

import com.noticiasegg.Repositorios.NoticiaRepositorio;
import com.noticiasegg.entidades.Noticia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author user
 */
@Service
public class NoticiaServicio {

    private final NoticiaRepositorio noticiaRepository;

    /**
     *
     * @param noticiaRepository
     */
    @Autowired
    public NoticiaServicio(NoticiaRepositorio noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public void guardarNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    public List<Noticia> obtenerTodasLasNoticias() {
        return noticiaRepository.findAll();
    }

    public void crearNoticia(String titulo, String cuerpo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Noticia> listaNoticias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  


}