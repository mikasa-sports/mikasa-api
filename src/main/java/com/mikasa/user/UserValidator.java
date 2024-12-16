package com.mikasa.user;

import com.mikasa.dto.user.UserDto;

interface UserValidator<T extends UserDto> {

  void validate(T user);
}
