package com.okbo_projects.domain.user.service;

import com.okbo_projects.common.entity.User;
import com.okbo_projects.common.exception.CustomException;
import com.okbo_projects.common.model.SessionUser;
import com.okbo_projects.common.utils.PasswordEncoder;
import com.okbo_projects.common.utils.Team;
import com.okbo_projects.domain.user.model.request.LoginRequest;
import com.okbo_projects.domain.user.model.request.UserCreateRequest;
import com.okbo_projects.domain.user.model.response.UserCreateResponse;
import com.okbo_projects.domain.user.model.response.UserGetMyProfileResponse;
import com.okbo_projects.domain.user.model.response.UserGetOtherProfileResponse;
import com.okbo_projects.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.EntityResponse;

import static com.okbo_projects.common.exception.ErrorMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public UserCreateResponse create(UserCreateRequest request) {

        if (userRepository.existsUserByNickname((request.getNickname()))) {
            throw new CustomException(CONFLICT_NICKNAME);
        }

        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new CustomException(CONFLICT_EMAIL);
        }

        String encodingPassword = passwordEncoder.encode(request.getPassword());

        Team team = Team.valueOf(request.getFavoriteTeam());

        User user = new User(
                request.getNickname(),
                request.getEmail(),
                encodingPassword,
                team
        );

        userRepository.save(user);

        return new UserCreateResponse(user);
    }

    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_EMAIL));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(UNAUTHORIZED_PASSWORD);
        }

        return new SessionUser(user.getId(), user.getEmail(), user.getNickname());
    }














































































































































































































































































































































    @Transactional(readOnly = true)
    public UserGetMyProfileResponse getMyProfile(SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getUserId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        return new UserGetMyProfileResponse(
                user
        );
    }

    @Transactional(readOnly = true)
    public UserGetOtherProfileResponse getOtherProfile(String userNickname) {
        User user = userRepository.findByNickname(userNickname)
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        return new UserGetOtherProfileResponse(
                user
        );
    }
    }




