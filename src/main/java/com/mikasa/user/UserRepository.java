package com.mikasa.user;

import com.mikasa.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
interface UserRepository extends JpaRepository<User, UUID> {

  User findByEmail(String email);

  boolean existsByEmailIgnoreCase(String email);

  boolean existsByPhone(String phone);

}
