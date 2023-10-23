/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasegg.controladores;

import com.noticiasegg.entidades.Noticia;
import com.noticiasegg.entidades.Usuario;
import com.noticiasegg.exceptions.MiException;
import com.noticiasegg.servicios.NoticiaServicio;
import com.noticiasegg.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @GetMapping("/inicio")
    public String mostrarNoticias(ModelMap modelo, HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuarioSession");

//        if (logueado.getRol().toString().equals("ADMIN")) {
//            return "redirect:/admin/dashboard";
//        }
        List<Noticia> noticias = noticiaServicio.listaNoticias();
        modelo.addAttribute("noticias", noticias);
        modelo.put("usuario", usuarioServicio.getOne(logueado.getId()));
        return "inicio.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro_form.html";
    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, @RequestParam String nombreUsuario, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(archivo, nombreUsuario, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");
            return "redirect:/inicio";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "registro_form.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioSession");
        modelo.put("usuario", usuario);
        return "usuario_modificar.html";
    }

    @PreAuthorize("hasAnyRole ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PERIODISTA')")
    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo,@PathVariable String id, @RequestParam String nombreUsuario, 
            @RequestParam String password, @RequestParam String password2, ModelMap modelo) throws IOException {

        System.out.println(id);
        try {
            usuarioServicio.actualizar(archivo, id, nombreUsuario, password, password2);
            modelo.put("exito", "Usuario actualizado correctamente");
            return "redirect:/inicio";
        } catch (MiException ex) {
            ex.printStackTrace();
            modelo.put("error", ex.getMessage());
            //Necesitaba modelar de vuelta el usuario porque si no no puede recargar /perfil!!!
            modelo.put("usuario", usuarioServicio.getOne(id));
        }

        return "usuario_modificar.html";

    }

}