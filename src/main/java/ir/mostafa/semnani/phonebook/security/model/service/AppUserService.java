package ir.mostafa.semnani.phonebook.security.model.service;

import ir.mostafa.semnani.phonebook.security.model.dto.AppUserDTO;
import ir.mostafa.semnani.phonebook.security.model.entity.AppUser;
import ir.mostafa.semnani.phonebook.security.model.mapper.AppUserMapper;
import ir.mostafa.semnani.phonebook.security.model.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppRoleService appRoleService;

    @Transactional(readOnly = true)
    public List<AppUserDTO> findAll() {
        List<AppUser> appUsers = appUserRepository.findAll();
        return AppUserMapper.toDTOs(appUsers);
    }

    @Transactional(readOnly = true)
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AppUserDTO save(AppUserDTO appUserDTO) {
        AppUser savedAppUser = appUserRepository.save(AppUserMapper.toEntity(appUserDTO));
        if (appUserDTO.getRoles() != null && !appUserDTO.getRoles().isEmpty()) {
            appUserDTO.getRoles().forEach(appRole -> appRole.setUsers(Set.of(savedAppUser)));
            appRoleService.saveAll(appUserDTO.getRoles());
        }
        return appUserDTO;
    }

}
