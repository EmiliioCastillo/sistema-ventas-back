package com.projectjava.demosclient.dto;

import lombok.Builder;
import lombok.Data;
//  AuthResponse es la respuesta que retornarán esos endpoints: el JWT como String.
public class AuthResponse {
    private String token;
    private String rol; // Nuevo campo para el rol del usuario

    private AuthResponse() {
        // Constructor privado para asegurar que la instancia solo pueda ser creada a través del Builder
    }

    public String getToken() {
        return token;
    }

    public String getRol() {
        return rol;
    }

    // Clase Builder estática dentro de AuthResponse
    public static class Builder {
        private String token;
        private String rol;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder rol(String rol) {
            this.rol = rol;
            return this;
        }

        public AuthResponse build() {
            AuthResponse authResponse = new AuthResponse();
            authResponse.token = this.token;
            authResponse.rol = this.rol;
            return authResponse;
        }
    }

    // Método estático para obtener una nueva instancia del Builder
    public static Builder builder() {
        return new Builder();
    }
}

