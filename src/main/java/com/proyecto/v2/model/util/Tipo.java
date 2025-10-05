package com.proyecto.v2.model.util;

public enum Tipo {
    AMBIENTAL("Ambiental"),
    COMUNITARIA("Comunitaria"),
    EDUCATIVA("Educativa"),
    SALUD("Salud"),
    DERECHOS_HUMANOS("Derechos Humanos"),
    ANIMAL("Protección Animal"),
    CULTURAL("Cultural"),
    DEPORTIVA("Deportiva"),
    ASISTENCIA_SOCIAL("Asistencia Social"),
    INFANCIA("Infancia y Juventud"),
    DELINCUENCIA("Delincuencia");

    private final String tipoString;

    Tipo(String tipoString) {
        this.tipoString = tipoString;
    }

    public String getTipoString() {
        return tipoString;
    }

    // Método útil para obtener el enum desde un string (por ejemplo desde un formulario)
    public static Tipo fromString(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        for (Tipo tipo : Tipo.values()) {
            if (tipo.name().equalsIgnoreCase(texto) ||
                    tipo.tipoString.equalsIgnoreCase(texto)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("No se encontró el tipo: " + texto);
    }
}