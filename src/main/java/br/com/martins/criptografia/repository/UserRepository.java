package br.com.martins.criptografia.repository;

import br.com.martins.criptografia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
