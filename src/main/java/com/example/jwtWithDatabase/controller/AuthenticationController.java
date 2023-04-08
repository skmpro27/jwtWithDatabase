package com.example.jwtWithDatabase.controller;

import com.example.jwtWithDatabase.config.JwtTokenUtil;
import com.example.jwtWithDatabase.modal.JwtResponse;
import com.example.jwtWithDatabase.modal.UserDto;
import com.example.jwtWithDatabase.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto requestBody) throws Exception {
        authenticate(requestBody.getUsername(), requestBody.getPassword());
        final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(requestBody.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto requestBody) {
        return ResponseEntity.ok(jwtUserDetailService.saveUser(requestBody));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USER_DISABLE", disabledException);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("INVALID_CREDENTIAL", badCredentialsException);
        }
    }

}
