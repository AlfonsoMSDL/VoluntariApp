package com.proyecto.v2.model.util;

import java.util.Arrays;
import java.util.List;

public enum Rol {
    ADMIN("ADMIN"),
    ORGANIZACION("ORGANIZACION"),
    VOLUNTARIO("VOLUNTARIO");

    private String nombre;
    Rol(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Rol obtenerRol(String nombreBuscar){
        List<Rol> roles = Arrays.asList(Rol.values());

        for(Rol rol : roles){
            if(rol.getNombre().equals(nombreBuscar)){
                return rol;
            }
        }
        return null;
    }
}
