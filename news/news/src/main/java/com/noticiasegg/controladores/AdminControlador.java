package com.noticiasegg.controladores;

import com.noticiasegg.entidades.Usuario;
import com.noticiasegg.enumeraciones.Rol;
import com.noticiasegg.exceptions.MiException;
import com.noticiasegg.servicios.AdminServicio;
import com.noticiasegg.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private AdminServicio adminServicio;
//
//    @Autowired
//    private NoticiaServicio noticiaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

//    @GetMapping("/dashboard")
//    public String panelAdministrativo(ModelMap modelo) {
//        List<Noticia> noticias = noticiaServicio.listaNoticias();
//        modelo.addAttribute("noticias", noticias);
//        return "inicio.html";
//    }
    //Registro de periodistas por parte de un admin
    @GetMapping("/registrar")
    public String registrarPeriodista() {
        return "periodista_form.html";
    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, @RequestParam String nombreUsuario, @RequestParam String password, @RequestParam String password2, @RequestParam Integer sueldoMensual, ModelMap modelo) {

        try {
            adminServicio.registrarPeriosita(archivo, nombreUsuario, password, password2, sueldoMensual);
            modelo.put("exito", "Periodista registrado correctamente");
            return "redirect:/inicio";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "periodista_form.html";
        }
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = adminServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "lista_usuarios.html";
    }

    @GetMapping("/periodistas")
    public String listarPeriodistas(ModelMap modelo) {
        List<Usuario> periodistas = adminServicio.listarPeriodistas();
        modelo.addAttribute("periodistas", periodistas);

        return "lista_periodistas.html";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id, @RequestParam String rol) {
        Rol nuevoRol = Rol.valueOf(rol); // Convertir el valor de String a enum
        try {
            adminServicio.cambiarRol(id, nuevoRol);
        } catch (MiException ex) {
            System.out.println(ex.getMessage());
        }
        return "redirect:/admin/usuarios";
    }

    //modificar usuarios desde admin
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));

        return "panel.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(MultipartFile archivo, @PathVariable String id, String nombreUsuario, String password, String password2, ModelMap modelo) {
        //usuarioServicio.actualizar(archivo, id, nombreUsuario, password, password2);
        List<Usuario> usuarios = adminServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "redirect:/admin/usuarios";

    }

    @GetMapping("/estadoPeriodista/{id}")
    public String estadoPeriodista(@PathVariable String id) {
        adminServicio.darDeBaja(id);
        return "redirect:/admin/periodistas";
    }

    @GetMapping("/estadoUsuario/{id}")
    public String estadoUsuario(@PathVariable String id) {
        adminServicio.darDeBaja(id);
        return "redirect:/admin/usuarios";
    }

}
