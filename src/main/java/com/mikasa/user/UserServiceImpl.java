package com.mikasa.user;

import static com.mikasa.dto.JwtTokenType.ACCESS_TOKEN;
import static com.mikasa.exception.Error.INVALID_CREDENTIALS;
import static com.mikasa.exception.Error.INVALID_REFRESH_TOKEN;
import static com.mikasa.exception.Error.NOT_ACTIVE_ACCOUNT;

import com.mikasa.dto.AuthenticationRequestDto;
import com.mikasa.dto.AuthenticationResponseDto;
import com.mikasa.dto.JwtTokenType;
import com.mikasa.dto.user.RefreshTokenRequestDto;
import com.mikasa.dto.user.Role;
import com.mikasa.dto.user.UserRegistrationRequestDto;
import com.mikasa.dto.user.UserResponseDto;
import com.mikasa.entity.RefreshToken;
import com.mikasa.entity.RefreshTokenId;
import com.mikasa.entity.User;
import com.mikasa.exception.AuthenticationException;
import com.mikasa.exception.EntityAlreadyVerifiedException;
import com.mikasa.mailsender.MailService;
import com.mikasa.security.JwtTokenProvider;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserMapper userMapper;
  private final MailService mailService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final RefreshTokenRepository refreshTokenRepository;
  private final List<UserCreationValidator> userCreationValidators;

  UserServiceImpl(UserMapper userMapper, MailService mailService,
      UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtTokenProvider jwtTokenProvider, RefreshTokenRepository refreshTokenRepository,
      List<UserCreationValidator> userCreationValidators) {
    this.userMapper = userMapper;
    this.mailService = mailService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
    this.refreshTokenRepository = refreshTokenRepository;
    this.userCreationValidators = userCreationValidators;
  }

  @Override
  public UserResponseDto register(UserRegistrationRequestDto dto) {
    userCreationValidators.forEach(validator -> validator.validate(dto));
    User user = userMapper.mapToEntity(dto);
    user = userRepository.save(user.toBuilder()
        .role(Role.BUYER)
        .build());
    log.info("New userEntity registered with email: {}", user.getEmail());

    UserResponseDto userDto = userMapper.mapToResponseDto(user);
    mailService.sendEmail(userDto);
    log.info("Verification email was sent to userEntity {}", user.getEmail());
    return userDto;
  }

  @Override
  public UserResponseDto activate(String token) {
    User user = userRepository.findByEmail(token);
    if (user.isEmailVerified()) {
      throw new EntityAlreadyVerifiedException(null);
    }

    user = userRepository.saveAndFlush(user.toBuilder()
        .emailVerified(true)
        .build());
    log.info("User {} account was verified by email", user.getEmail());
    return userMapper.mapToResponseDto(user);
  }

  public AuthenticationResponseDto login(AuthenticationRequestDto dto) {
    User user = userRepository.findByEmail(dto.getUsername());
    if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new AuthenticationException(INVALID_CREDENTIALS, Map.of("username", dto.getUsername()));
    }

    if (!user.isEmailVerified()) {
      throw new AuthenticationException(NOT_ACTIVE_ACCOUNT);
    }

    String accessToken = jwtTokenProvider.createJwt(user, ACCESS_TOKEN);
    String refreshToken = jwtTokenProvider.createJwt(user, JwtTokenType.REFRESH_TOKEN);

    refreshTokenRepository.save(RefreshToken.builder()
        .id(RefreshTokenId.builder()
            .user(user)
            .refreshToken(refreshToken)
            .build())
        .build());

    return AuthenticationResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto dto) {
    RefreshToken refreshToken = refreshTokenRepository.findById_RefreshToken(dto.getRefreshToken())
        .orElseThrow(() -> new AuthenticationException(INVALID_REFRESH_TOKEN));

    User user = userRepository.getReferenceById(
        refreshToken.getId().getUser().getId());
    log.info("Access token is refreshed for user {}", user.getEmail());
    return AuthenticationResponseDto.builder()
        .accessToken(jwtTokenProvider.createJwt(user, ACCESS_TOKEN))
        .refreshToken(dto.getRefreshToken())
        .build();
  }
}
