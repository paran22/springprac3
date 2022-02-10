package com.sparta.week03project.service;

import com.sparta.week03project.dto.SignupRequestDto;
import com.sparta.week03project.entity.User;
import com.sparta.week03project.entity.UserRoleEnum;
import com.sparta.week03project.exception.CustomException;
import com.sparta.week03project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sparta.week03project.exception.ErrorCode.DUPLICATE_USER_NAME;
import static com.sparta.week03project.exception.ErrorCode.INVALID_ADMIN_TOKEN;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(DUPLICATE_USER_NAME);
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        String name = requestDto.getNickName();
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(INVALID_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
            name = requestDto.getName();
        }
            User user = new User(username, password, name, role);
            userRepository.save(user);
            return user;
    }

}