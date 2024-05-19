
package com.projectjava.demosclient.security;

import com.projectjava.demosclient.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* Esta clase contiene la SecurityFilterChain. Todas las requests que
*  reciba nuestra API pasarán por esta cadena de filtros.
Le indicamos que los endpoints en la ruta /auth/ (login y registro)
* serán públicos (son los de la clase AuthController, que hicimos en el punto 2#) .
Para acceder a los demás endpoints, el usuario deberá estar autenticado
*  ( .anyRequest().authenticated() )
Deshabilitamos csrf y session. Son métodos predeterminados de
*  Spring Security que no usaremos, porque la autenticación la haremos con JWT.
Agregamos el jwtAuthenticationFilter (lo desarrollaremos luego).
El authenticationProvider es el responsable de recibir una solicitud
* de autorización y decidir si es válida o no. Más adelante,
* en otra clase de configuración indicaremos cuál provider implementaremos.
La anotación @EnableMethodSecurity(securedEnabled = true)
*  nos permitirá incluir en los controladores la anotación
*  @Secured para indicar el ROL de los usuarios que tendrán acceso a los mismos.
*
* */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
     AuthenticationProvider authProvider;


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable()).authorizeHttpRequests(authRequest -> {
                    authRequest.requestMatchers("api/v1/auth/**").
                            permitAll();
                    authRequest.requestMatchers("api/v1/pagos/**").
                            permitAll();
                    authRequest.requestMatchers("api/v1/ventas/**").hasAnyAuthority("ADMINISTRADOR", "EMPLEADO_VENTA").
                    requestMatchers("api/v1/gastos/**").hasAnyAuthority("ADMINISTRADOR", "EMPLEADO_VENTA").
                    requestMatchers("api/v1/productos/**").hasAuthority("ADMINISTRADOR").
                            requestMatchers("api/v1/productos/upload").hasAuthority("ADMINISTRADOR").

                            requestMatchers("api/v1/proveedores/**").hasAuthority("ADMINISTRADOR").
                            requestMatchers("api/v1/usuarios/**").hasAuthority("ADMINISTRADOR").
                    requestMatchers("api/v1/movimientos/**").hasAuthority("ADMINISTRADOR").
                    requestMatchers("api/v1/clientes/**").hasAnyAuthority("ADMINISTRADOR", "EMPLEADO_VENTA").
                            anyRequest().authenticated();
                })
                .sessionManagement(sessionManager -> sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}









