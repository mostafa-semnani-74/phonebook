package ir.mostafa.semnani.phonebook.security.controller;

import ir.mostafa.semnani.phonebook.security.model.dto.AppUserDTO;
import ir.mostafa.semnani.phonebook.security.model.entity.AppUser;
import ir.mostafa.semnani.phonebook.security.model.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AppUserDTO>> findAll() {
        return ResponseEntity.ok(appUserService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO appUserDTO) {
        return new ResponseEntity<>(appUserService.save(appUserDTO), HttpStatus.CREATED);
    }

}
