package com.psuti.books.service;

import com.psuti.books.dto.UserDTO;
import com.psuti.books.model.User;
import com.psuti.books.repository.UserRepository;
import com.psuti.books.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User create(UserDTO dto) {
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .secondName(dto.getSecondName())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .createdAt(new Date())
                .enabled(true)
                .role("ROLE_USER")
                .build());
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User update(UserDTO dto, UserPrincipal principal) {
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(dto.getId()).orElseThrow();
        return userRepository.save(User.builder()
                .id(userRepository.findByEmail(principal.getEmail()).getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .secondName(dto.getSecondName())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .avatar(dto.getAvatar())
                .createdAt(user.getCreatedAt())
                .enabled(user.isEnabled())
                .role(user.getRole())
                .build());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEnabled(false);
        return userRepository.save(user);
    }

    public User unbanUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public boolean checkEnabledPrincipal(UserPrincipal principal) {
        return userRepository.findByEmail(principal.getEmail()).isEnabled();
    }
}
