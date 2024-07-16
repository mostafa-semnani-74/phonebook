package ir.mostafa.semnani.phonebook.security.controller;

import ir.mostafa.semnani.phonebook.security.model.entity.AppPermission;
import ir.mostafa.semnani.phonebook.security.model.service.AppPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/permissions")
@RequiredArgsConstructor
public class AppPermissionController {
    private final AppPermissionService appPermissionService;

    @GetMapping
    public ResponseEntity<List<AppPermission>> findAll() {
        return ResponseEntity.ok(appPermissionService.findAll());
    }
}
