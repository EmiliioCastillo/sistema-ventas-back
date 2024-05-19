package com.projectjava.demosclient.security.jwt;

import com.projectjava.demosclient.security.jwt.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/*
*
    El filtro hereda de OncePerRequestFilter (se ejecutará una vez sola por cada request).
    Obtenemos el token que viene incluido en la request llamando al método getTokenFromRequest
    *  (ver más abajo). El mismo busca el token que está en el HEADER de la request
    * y le quita la palabra "Bearer".
    Si la request no tiene JWT, continuamos con la cadena de filtros,
    *  donde habíamos indicado que solo podría acceder al login y registro en /auth/.
    Si la request viene con un JWT, buscará el usuario en nuestra Base de Datos.
    * Luego lo validará (credenciales correctas, no expirado) y si está todo ok
    *
    * lo guardará en el SecurityContextHolder.
    SecurityContextHolder es un método estático para recuperar los datos del usuario.
    *  Permitirá llamarlo desde cualquier parte de nuestro código sin pasarle ningún parámetro.

*
*
* */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
   JwtService jwtService;
    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        final String username;
        if (token==null)
        {
            filterChain.doFilter(request, response);
            return;
        }
        username= jwtService.getUsernameFromToken(token);

        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer"))
        {
            return authHeader.substring(7);
        }
        return null;
    }
}