package com.mikasa.user;

import static com.mikasa.exception.Error.USER_ALREADY_EXIST_WITH_EMAIL;
import static com.mikasa.exception.Error.USER_ALREADY_EXIST_WITH_PHONE;

import com.mikasa.exception.EntityAlreadyExistException;
import com.mikasa.util.BooleanUtils;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserValidatorConfig {

  private final UserRepository repository;

  public UserValidatorConfig(UserRepository repository) {
    this.repository = repository;
  }

  @Bean
  UserCreationValidator phoneNumberValidator() {
    return user -> {
      String phone = user.getPhone();
      BooleanUtils.throwIfTrue(repository.existsByPhone(phone),
          () -> new EntityAlreadyExistException(USER_ALREADY_EXIST_WITH_PHONE,
              Map.of("phone", phone)));
    };
  }

  @Bean
  UserCreationValidator emailValidator() {
    return user -> {
      String email = user.getEmail();
      BooleanUtils.throwIfTrue(repository.existsByEmailIgnoreCase(email),
          new EntityAlreadyExistException(USER_ALREADY_EXIST_WITH_EMAIL,
              Map.of("email", email)));
    };
  }
}
