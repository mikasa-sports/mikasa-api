package com.mikasa.user;

import com.mikasa.entity.RefreshToken;
import com.mikasa.entity.RefreshTokenId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, RefreshTokenId> {
  Optional<RefreshToken> findById_RefreshToken(String refreshToken);
}
