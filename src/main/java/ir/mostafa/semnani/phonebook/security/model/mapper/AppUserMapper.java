package ir.mostafa.semnani.phonebook.security.model.mapper;

import ir.mostafa.semnani.phonebook.security.model.dto.AppUserDTO;
import ir.mostafa.semnani.phonebook.security.model.entity.AppUser;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserMapper {
    public static AppUser toEntity(AppUserDTO appUserDTO)  {
        return AppUser.builder()
                .id(appUserDTO.getId())
                .username(appUserDTO.getUsername())
                .password(appUserDTO.getPassword())
                .roles(appUserDTO.getRoles())
                .build();
    }

    public static AppUserDTO toDTO(AppUser appUser) {
        return AppUserDTO.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .roles(appUser.getRoles())
                .build();
    }

    public static List<AppUserDTO> toDTOs(List<AppUser> appUsers) {
        return appUsers.stream()
                .map(AppUserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
