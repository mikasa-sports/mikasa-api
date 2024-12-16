package com.mikasa.user;

import com.mikasa.dto.AuthenticationRequestDto;
import com.mikasa.dto.AuthenticationResponseDto;
import com.mikasa.dto.user.RefreshTokenRequestDto;
import com.mikasa.dto.user.UserResponseDto;
import com.mikasa.dto.user.UserRegistrationRequestDto;

public interface UserService {

  UserResponseDto register(UserRegistrationRequestDto dto);

  UserResponseDto activate(String token);

  AuthenticationResponseDto login(AuthenticationRequestDto dto);

  AuthenticationResponseDto refreshToken(RefreshTokenRequestDto dto);
}
