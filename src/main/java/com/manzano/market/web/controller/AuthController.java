package com.manzano.market.web.controller;

import com.manzano.market.dominio.dto.AuthenticationRequest;
import com.manzano.market.dominio.dto.AuthenticationResponse;
import com.manzano.market.dominio.servicio.UserDetailService;
import com.manzano.market.web.Security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> crearToken(@RequestBody AuthenticationRequest request){

        try {
            /*Toma los parametros del request para validarlos con la interfaz authManager de Spring Security*/
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getContra()));
            /*Aca carga los datos predefinidos en el userDetailService*/
            UserDetails userDetails =  userDetailService.loadUserByUsername(request.getUsuario());
            /*Aca genera el token si las credenciales son correctas*/
            String jwt = jwtUtil.generarToken(userDetails);
            /*Si todo salio bien se envia el response con el token correcto*/
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e){
            /*Si las credenciales no son correctas, aca da el rechazo*/
            System.out.println("se pudrio");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
