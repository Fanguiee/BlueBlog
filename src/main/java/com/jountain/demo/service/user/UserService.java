package com.jountain.demo.service.user;

import com.jountain.demo.dto.UserDto;
import com.jountain.demo.exceptions.AlreadyExistsException;
import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.User;
import com.jountain.demo.repository.UserRepository;
import com.jountain.demo.request.UserCreateRequest;
import com.jountain.demo.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(UserCreateRequest request) {
        /*return Optional.of(request)
                .filter(req->!userRepository.existsByEmail(request.getEmail()))
                .map(req->{
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setPassword(request.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(()->new RuntimeException("User with the email already exists"));*/
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(String.format("User with the email %s already exists",request.getEmail()));
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(user ->{
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                })
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete,
                () -> {throw new ResourceNotFoundException("User not found");});
    }

    @Override
    public UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
