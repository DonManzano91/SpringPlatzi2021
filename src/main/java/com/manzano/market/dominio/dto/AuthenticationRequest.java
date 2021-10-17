package com.manzano.market.dominio.dto;

public class AuthenticationRequest {

    private String Usuario;
    private String Contra;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContra() {
        return Contra;
    }

    public void setContra(String contra) {
        Contra = contra;
    }
}
