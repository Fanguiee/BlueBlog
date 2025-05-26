package com.jountain.demo.service.user;

import com.jountain.demo.model.User;
import com.jountain.demo.request.UserCreateRequest;
import com.jountain.demo.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(UserCreateRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

}
