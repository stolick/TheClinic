package mk.ukim.finki.dipl.usermanagement.usermanagement.xport.rest;


import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.exceptions.RoleNotFoundException;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Patient;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.Role;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.User;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.models.enums.ERole;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.RoleRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.DoctorService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.PatientService;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.security.jwt.JwtUtils;
import mk.ukim.finki.dipl.usermanagement.usermanagement.service.security.services.UserDetailsImpl;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.LoginRequest;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.request.RegisterRequest;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.response.JwtResponse;
import mk.ukim.finki.dipl.usermanagement.usermanagement.xport.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = new User(registerRequest.getUsername(),
                encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROlE_PATIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "doctor":
                        Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROlE_PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        user.getRoles().stream().forEach(role ->{
            if (role.getName() == ERole.ROLE_DOCTOR){
                doctorService.createDoctor(user);
            }else if(role.getName() == ERole.ROlE_PATIENT){
                patientService.createPatient(user);
            }else{
                throw new RoleNotFoundException();
            }
        });


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
