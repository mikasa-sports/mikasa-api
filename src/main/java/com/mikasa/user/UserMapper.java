package com.mikasa.user;

import com.mikasa.dto.user.UserResponseDto;
import com.mikasa.dto.user.UserRegistrationRequestDto;
import com.mikasa.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  UserMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User mapToEntity(UserRegistrationRequestDto userRegistrationRequestDto) {
    return User.builder()
        .name(userRegistrationRequestDto.getName())
        .surname(userRegistrationRequestDto.getSurname())
        .phone(userRegistrationRequestDto.getPhone())
        .email(userRegistrationRequestDto.getEmail())
        .password(passwordEncoder.encode(userRegistrationRequestDto.getPassword()))
        .build();
  }

  public UserResponseDto mapToResponseDto(User user) {
    return UserResponseDto.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .phone(user.getPhone())
        .email(user.getEmail())
        .role(user.getRole())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }
}
