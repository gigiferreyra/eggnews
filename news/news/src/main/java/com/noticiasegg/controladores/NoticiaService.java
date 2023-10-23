/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.controladores;

import com.noticiasegg.entidades.Noticia;
import java.util.List;

/**
 *
 * @author user
 */
class NoticiaService {
    
    private final NoticiaControlador outer;

    public NoticiaService(final NoticiaControlador outer) {
        this.outer = outer;
    }

    List<Noticia> obtenerTodasLasNoticias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void guardarNoticia(Noticia nuevaNoticia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
