package ir.mostafa.semnani.phonebook.security.model.repository;

import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
