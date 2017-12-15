package com.yizit.mes.controller;

import com.yizit.mes.domain.User;
import com.yizit.mes.service.impl.UserServiceImpl;
import com.yizit.mes.vo.Response;
import com.yizit.mes.vo.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(User authenticationRequest) throws AuthenticationException{
        final String token = userService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse(token,expiration);
        return ResponseEntity.ok(new Response("0","success",tokenResponse));
    }

}
