package ir.mostafa.semnani.phonebook.security.model.service;

import ir.mostafa.semnani.phonebook.security.model.AppUserDetailsService;
import ir.mostafa.semnani.phonebook.security.model.dto.AuthenticationRequest;
import ir.mostafa.semnani.phonebook.security.model.dto.AuthenticationResponse;
import ir.mostafa.semnani.phonebook.security.model.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        // TODO save the new user
        // TODO generate token
        throw new RuntimeException("not implemented yet");
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userDetailsService.loadUserByUsername(request.getUsername());
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
