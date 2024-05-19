package com.projectjava.demosclient.cors;
/*
    AuthenticationManager es una interfaz de de Spring Security,
    responsable de manejar el proceso de autenticación de usuarios.
    El proveedor de autenticación a implementar será DaoAuthenticationProvider,
    que valida las credenciales (usuario y contraseña) contra una Base de Datos.
     Otro proveedor utilizado comúnmente es OAuth2Login, que sirve para inciar sesión con Google,
      Facebook, etc.
    Para encriptar las contraseñas utilizaremos el algoritmo Bycrypt.
    UserDetailsService se encargará de buscar el usuario en la base de datos.
    Recordemos que habíamos definido que utilizaríamos como username el email.
    CORS (Cross-Origin Resource Sharing) es un mecanismo de seguridad que tienen
    los navegadores web para restringir peticiones HTTP entre distintos servidores.
    Es necesario agregar esta configuración para que el Front pueda acceder a nuestra API.
     Completa la línea de .allowedOrigins(... ) con la URL que utilizará el front-end.

*/


import com.projectjava.demosclient.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer{

    @Autowired
     UserDao usuarioDao;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(BpasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder BpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return username -> usuarioDao.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not fournd"));
    }


    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin("http://localhost:5173");
        cors.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN
        ));
        cors.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.DELETE.name()
        ));
        cors.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", cors);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;
    }

}