/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.servicios;

import com.noticiasegg.entidades.Imagen;
import com.noticiasegg.exceptions.MiException;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    //MultipartFile es el tipo de archivo en el que se va a almacenar la imagen
    public Imagen guardar(MultipartFile archivo) throws MiException {

        if (archivo != null) {
            try {

                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MiException, IOException{
      
        if (archivo != null) {
            Imagen imagen = new Imagen();
            if (idImagen != null) {
                Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                
                if (respuesta.isPresent()) {
                    imagen = respuesta.get();
                }
            }
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            return imagenRepositorio.save(imagen);
        }
        return null;
        
    }


    
    

}
