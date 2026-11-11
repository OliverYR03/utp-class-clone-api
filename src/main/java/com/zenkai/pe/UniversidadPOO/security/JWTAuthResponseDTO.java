package com.zenkai.pe.UniversidadPOO.security;

public class JWTAuthResponseDTO {
    private String tokendeAcceso;
    private String tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokendeAcceso) {
        this.tokendeAcceso = tokendeAcceso;
    }

    public String getTokendeAcceso() {
        return tokendeAcceso;
    }

    public void setTokendeAcceso(String tokendeAcceso) {
        this.tokendeAcceso = tokendeAcceso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }
    
    
}
