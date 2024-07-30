package com.psuti.books.service;

import com.psuti.books.dto.UserMsgDTO;
import com.psuti.books.model.UserMsg;
import com.psuti.books.repository.StatusRepository;
import com.psuti.books.repository.UserMsgRepository;
import com.psuti.books.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMsgService {
    private UserMsgRepository userMsgRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;
        public UserMsg create(UserMsgDTO dto) {
            return userMsgRepository.save(UserMsg.builder()
                    .user(userRepository.findById(dto.getUserId()).orElse(null))
                    .createAt(new Date())
                    .text(dto.getText())
                    .notes(dto.getNotes())
                    .status(statusRepository.findById(dto.getStatusId()).orElse(null))
                    .type(dto.getType())
                    .build());
        }

        public List<UserMsg> getByUserId(Long id) {
            return userMsgRepository.findByUserId(id);
        }

        public UserMsg getById(Long id) {
            return userMsgRepository.findById(id).orElse(null);
        }

        public UserMsg updateFromUser(UserMsgDTO dto) {
            return userMsgRepository.save(UserMsg.builder()
                    .id(dto.getId())
                    .user(userRepository.findById(dto.getUserId()).orElse(null))
                    .text(dto.getText())
                    .notes(dto.getNotes())
                    .status(statusRepository.findById(dto.getStatusId()).orElse(null))
                    .type(dto.getType())
                    .build());
        }

        public void delete(Long id) {
            userMsgRepository.deleteById(id);
        }
}
