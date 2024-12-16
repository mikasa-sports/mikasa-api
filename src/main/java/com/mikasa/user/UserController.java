package com.mikasa.user;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mikasa.dto.AuthenticationRequestDto;
import com.mikasa.dto.AuthenticationResponseDto;
import com.mikasa.dto.user.RefreshTokenRequestDto;
import com.mikasa.dto.user.UserRegistrationRequestDto;
import com.mikasa.dto.user.UserResponseDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
final class UserController implements GraphQLMutationResolver {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @MutationMapping
  UserResponseDto registerUser(@Argument("input") UserRegistrationRequestDto requestDto) {
    return userService.register(requestDto);
  }

  @MutationMapping
  UserResponseDto activateUser(@Argument("token") String token) {
    return userService.activate(token);
  }

  @MutationMapping
  AuthenticationResponseDto loginUser(@Argument("input") AuthenticationRequestDto dto) {
    return userService.login(dto);
  }

  @MutationMapping
  AuthenticationResponseDto refreshToken(@Argument("input") RefreshTokenRequestDto dto) {
    return userService.refreshToken(dto);
  }
}
