package be.bruxellesformation.bf_projet_final.security.controller;

import be.bruxellesformation.bf_projet_final.config.JwtProvider;
import be.bruxellesformation.bf_projet_final.security.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;

    @Autowired
    public SecurityController(AuthenticationManager authenticationManager, JwtProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(path = {"/login", "/signin"})
    public ResponseEntity<String> signInAction(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = tokenProvider.generateToken(auth);
        return ResponseEntity.ok(jwt);
    }
}