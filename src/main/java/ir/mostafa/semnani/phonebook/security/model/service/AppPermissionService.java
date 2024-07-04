package ir.mostafa.semnani.phonebook.security.model.service;

import ir.mostafa.semnani.phonebook.security.model.entity.AppPermission;
import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import ir.mostafa.semnani.phonebook.security.model.repository.AppPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class AppPermissionService {
    private final AppPermissionRepository appPermissionRepository;
    private final AppRoleService appRoleService;

    @Transactional(readOnly = true)
    public List<AppPermission> findAll() {
        return appPermissionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AppPermission> findByRoleId(Long roleId) {
        return appPermissionRepository.findByRoleId(roleId);
    }

    public AppPermission save(AppPermission appPermission, Long roleId) {
        AppRole appRole = appRoleService.findById(roleId);
        appPermission.setAppRoles(Set.of(appRole));
        return appPermissionRepository.save(appPermission);
    }
}
