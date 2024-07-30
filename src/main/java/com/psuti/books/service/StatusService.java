package com.psuti.books.service;

import com.psuti.books.dto.StatusDTO;
import com.psuti.books.model.Status;
import com.psuti.books.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusService {
    private StatusRepository statusRepository;
    public Status create(StatusDTO dto) {
        return statusRepository.save(Status.builder()
                .name(dto.getName())
                .build());
    }

    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    public Status getById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    public Status update(StatusDTO dto) {
        return statusRepository.save(Status.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build());
    }

    public void delete(Long id) {
        statusRepository.deleteById(id);
    }
}
