package com.proyecto.v2.service;

import com.proyecto.v2.persistence.UsuarioDao;
import com.proyecto.v2.presentation.VoluntarioController;
import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Usuario;
import com.proyecto.v2.model.Voluntario;
import org.apache.log4j.Logger;

import java.util.Optional;

public class AuthService {
    private final UsuarioDao usuarioDAo;

    Logger log = Logger.getLogger(VoluntarioController.class);

    public AuthService() {
        this.usuarioDAo = new UsuarioDao();
    }
    public Usuario Login (String correo, String clave){

        Usuario usuario = usuarioDAo.findByEmail(correo).get();
        if(usuario != null){
            if(clave.equals(usuario.getClave())){
                return usuario;
            }
        }
        return null;
    }


}
