/*
package com.example.mindboost.Security;


import com.example.mindboost.DTOs.PatientDTO;
import com.example.mindboost.DTOs.UserDTO;
import com.example.mindboost.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class SecurityController {
    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    Authentication authentication(Authentication authentication){
        return authentication;
    }

   @PostMapping("/login")
    public Map<String, String> login(String username, String password){
  Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
    );
  Instant instant=Instant.now();
  String scope=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
    JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
            .issuedAt(instant)
            .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
            .subject(username)
            .claim("scope",scope)
            .build();
    JwtEncoderParameters jwtEncoderParameters=
            JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS512).build(),
                    jwtClaimsSet
            );
    String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    return Map.of("access-token",jwt);

}

   @PostMapping("/signup")
   public ResponseEntity<?> signup(@RequestBody PatientDTO patientDTO){
       try {
           log.info("Received signup request for: " + patientDTO.getUserName());
           PatientDTO savedPatient = userService.SavePatient(patientDTO);
           if (savedPatient == null) {
               return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
           }
           return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>("An error occurred while creating the user", HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }


}
*/
