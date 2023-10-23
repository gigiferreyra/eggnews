/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class Periodista extends Usuario {
    
    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL)
    private List<Noticia>MisNoticias= new ArrayList<>();
    private Integer sueldoMensual;

    public Periodista() {
    }

    public List<Noticia> getMisNoticias() {
        return MisNoticias;
    }

    public void setMisNoticias(List<Noticia> MisNoticias) {
        this.MisNoticias = MisNoticias;
    }


    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }
    
    
}