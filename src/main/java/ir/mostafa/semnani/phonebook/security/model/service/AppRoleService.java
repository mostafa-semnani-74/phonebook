package ir.mostafa.semnani.phonebook.security.model.service;

import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import ir.mostafa.semnani.phonebook.security.model.repository.AppRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class AppRoleService {
    private final AppRoleRepository appRoleRepository;

    @Transactional(readOnly = true)
    public List<AppRole> findAll() {
        List<AppRole> appRoles = appRoleRepository.findAll();
        return appRoles;
    }

    @Transactional(readOnly = true)
    public AppRole findById(Long id) {
        Optional<AppRole> appRole = appRoleRepository.findById(id);
        if (appRole.isPresent())
            return appRole.get();
        else
            throw new RuntimeException("app role not found with id : " + id);
    }

    @Transactional(readOnly = true)
    public List<AppRole> findByUserId(Long userId) {
        List<AppRole> appRoles = appRoleRepository.findByUserId(userId);
        return appRoles;
    }

    public AppRole save(AppRole appRole) {
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return appRole;
    }

    public List<AppRole> saveAll(Set<AppRole> roles) {
        return appRoleRepository.saveAll(roles);
    }
}
