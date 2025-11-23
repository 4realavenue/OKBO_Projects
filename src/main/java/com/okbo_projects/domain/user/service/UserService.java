package com.okbo_projects.domain.user.service;

import com.okbo_projects.common.entity.User;
import com.okbo_projects.domain.user.model.request.UserCreateRequest;
import com.okbo_projects.domain.user.model.response.UserCreateResponse;
import com.okbo_projects.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreateResponse create(UserCreateRequest request) {

        User user = new User(
                request.getNickname(),
                request.getEmail(),
                request.getPassword()
                );

        userRepository.save(user);

        return new UserCreateResponse(user);

    }
}
