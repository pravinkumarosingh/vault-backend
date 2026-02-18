package com.project.vault.controller;

import com.project.vault.model.LoginRequest;
import com.project.vault.model.LoginResponse;
import com.project.vault.utility.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        if("admin".equals(loginRequest.getUsername()) && "admin123".equals(loginRequest.getPassword())) {
                log.info("username and password is valid proceeding with token generation");
                String token = jwtUtil.generateToken(loginRequest.getUsername());

                return ResponseEntity.ok(
                        new LoginResponse(token, loginRequest.getUsername())
                );
        }

        return ResponseEntity.status(401).build();
    }

}
