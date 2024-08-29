package com.nexters.gaetteok.user.application;

import com.nexters.gaetteok.domain.User;
import com.nexters.gaetteok.jwt.JwtTokenGenerator;
import com.nexters.gaetteok.persistence.entity.UserEntity;
import com.nexters.gaetteok.persistence.entity.UserPushNotificationEntity;
import com.nexters.gaetteok.persistence.repository.UserPushNotificationRepository;
import com.nexters.gaetteok.persistence.repository.UserRepository;
import com.nexters.gaetteok.user.mapper.UserMapper;
import com.nexters.gaetteok.user.presentation.request.SignupRequest;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthApplication {

    private final JwtTokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final UserPushNotificationRepository userPushNotificationRepository;

    private static final int MAX_ATTEMPT = 10;
    private static final int USER_CODE_LENGTH = 6;

    @Transactional
    public String signup(SignupRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findByOauthIdentifier(
            request.getOauthIdentifier());
        UserEntity userEntity = userEntityOptional.orElseGet(() -> createUser(request));
        userPushNotificationRepository.save(
            new UserPushNotificationEntity(null, userEntity.getId(), 0, false));
        return tokenGenerator.generateToken(UserMapper.toDomain(userEntity));
    }

    @Transactional
    public Optional<String> getUserToken(String oauthIdentifier, String deviceToken) {
        Optional<UserEntity> userEntityOptional = userRepository.findByOauthIdentifier(
            oauthIdentifier);
        if (userEntityOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity userEntity = userEntityOptional.get();
        User user = UserMapper.toDomain(userEntity);
        user.setDeviceToken(deviceToken);
        userEntity = userRepository.save(UserMapper.toEntity(user));
        return Optional.of(tokenGenerator.generateToken(UserMapper.toDomain(userEntity)));
    }

    private UserEntity createUser(SignupRequest request) {
        UserEntity userEntity = UserEntity.builder()
            .oauthIdentifier(request.getOauthIdentifier())
            .deviceToken(request.getDeviceToken())
            .nickname(request.getNickname())
            .profileUrl(request.getProfileUrl())
            .code(createUserCode())
            .city(request.getCity())
            .authProvider(request.getProvider())
            .build();
        return userRepository.save(userEntity);
    }

    private String createUserCode() {
        for (int i = 0; i < MAX_ATTEMPT; i++) {
            String code = createRandomString();
            if (userRepository.findByCode(code).isEmpty()) {
                return code;
            }
        }
        throw new IllegalArgumentException("유저 코드 생성에 실패했습니다.");
    }

    private String createRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < USER_CODE_LENGTH; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

}
