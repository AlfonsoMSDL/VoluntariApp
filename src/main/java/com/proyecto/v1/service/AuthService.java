package com.proyecto.v1.service;

import com.proyecto.v1.controller.VoluntarioController;
import com.proyecto.v1.dto.response.GetOrganizacion;
import com.proyecto.v1.dto.response.GetUsuario;
import com.proyecto.v1.dto.response.GetVoluntario;
import com.proyecto.v1.mapper.GenericMapper;
import com.proyecto.v1.model.Organizacion;
import com.proyecto.v1.model.Usuario;
import com.proyecto.v1.model.Voluntario;
import com.proyecto.v1.service.OrganizacionService;
import com.proyecto.v1.service.VoluntarioService;
import org.apache.log4j.Logger;

import java.util.Optional;

public class AuthService {
    private VoluntarioService voluntarioService;
    private OrganizacionService organizacionService;
    private GenericMapper<Usuario, GetUsuario>  usuarioMapper;
    Logger log = Logger.getLogger(VoluntarioController.class);

    public AuthService() {
        this.voluntarioService = new VoluntarioService();
        this.organizacionService = new OrganizacionService();
        usuarioMapper = new  GenericMapper<>();
    }
    public GetUsuario Login (String correo, String clave){

        Optional<Organizacion> organizacionOptional = organizacionService.findByCorreo(correo);
        if(organizacionOptional != null && organizacionOptional.isPresent()){ //Se encontro el correo como una organizacion
            Organizacion organizacionEncontrada = organizacionOptional.get();
            log.info(organizacionEncontrada.toString()+"\n");
            log.info(organizacionService.findAllOrganizaciones().toString()+"\n");


            //Compruebo si la clave que digitó es igual a la guardada en la BD
            if(clave.equals(organizacionEncontrada.getClave())){
                //Convierto la organizacion a un usuario para devolverla
                Usuario usuario = (Usuario) organizacionEncontrada;
                return usuarioMapper.toDto(usuario,GetUsuario.class);
            }
        }


        //En caso de que no sea una organizacion, pregunto si es un voluntario
        Optional<Voluntario> voluntarioOptional = voluntarioService.findByCorreo(correo);
        if(voluntarioOptional != null && voluntarioOptional.isPresent()){ //Se encontro el correo como un voluntario
            Voluntario voluntarioEncontrado = voluntarioOptional.get();

            //Compruebo si la clave que digitó es igual a la guardada en la BD
            if(clave.equals(voluntarioEncontrado.getClave())){
                //Convierto la voluntario a un usuario para devolverla
                Usuario usuario = (Usuario) voluntarioEncontrado;
                return usuarioMapper.toDto(usuario,GetUsuario.class);
            }

        }

        //Si ninguna de las opciones es valida, significa que no se encontró el usuario
        return null;
    }
}
