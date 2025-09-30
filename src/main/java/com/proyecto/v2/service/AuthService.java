package com.proyecto.v2.service;

import com.proyecto.v2.presentation.VoluntarioController;
import com.proyecto.v2.model.Organizacion;
import com.proyecto.v2.model.Usuario;
import com.proyecto.v2.model.Voluntario;
import org.apache.log4j.Logger;

import java.util.Optional;

public class AuthService {
    private VoluntarioService voluntarioService;
    private OrganizacionService organizacionService;

    Logger log = Logger.getLogger(VoluntarioController.class);

    public AuthService() {
        this.voluntarioService = new VoluntarioService();
        this.organizacionService = new OrganizacionService();
    }
    public Usuario Login (String correo, String clave){

        Optional<Organizacion> organizacionOptional = organizacionService.findByCorreo(correo);
        if(organizacionOptional != null && organizacionOptional.isPresent()){ //Se encontro el correo como una organizacion
            Organizacion organizacionEncontrada = organizacionOptional.get();
            log.info(organizacionService.findAllOrganizaciones().toString()+"\n");


            //Compruebo si la clave que digitó es igual a la guardada en la BD
            if(clave.equals(organizacionEncontrada.getClave())){
                //Convierto la organizacion a un usuario para devolverla
                Usuario usuario = (Usuario) organizacionEncontrada;
                return usuario;
            }
        }


        //En caso de que no sea una organizacion, pregunto si es un voluntario
        Optional<Voluntario> voluntarioOptional = voluntarioService.findByCorreo(correo);
        if(voluntarioOptional != null && voluntarioOptional.isPresent()){ //Se encontro el correo como un voluntario
            Voluntario voluntarioEncontrado = voluntarioOptional.get();
            log.info(voluntarioService.findAllVoluntarios().toString()+"\n");

            //Compruebo si la clave que digitó es igual a la guardada en la BD
            if(clave.equals(voluntarioEncontrado.getClave())){
                //Convierto la voluntario a un usuario para devolverla
                Usuario usuario = (Usuario) voluntarioEncontrado;
                return usuario;
            }

        }

        //Si ninguna de las opciones es valida, significa que no se encontró el usuario
        return null;
    }
}
