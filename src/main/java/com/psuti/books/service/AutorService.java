package com.psuti.books.service;

import com.psuti.books.dto.AutorDTO;

import com.psuti.books.model.Autor;

import com.psuti.books.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AutorService {
    private AutorRepository autorRepository;
    public Autor create(AutorDTO dto) {
        return autorRepository.save(Autor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build());
    }

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Autor getById(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public Autor update(AutorDTO autor) {
        return autorRepository.save(Autor.builder()
                .id(autor.getId())
                .firstName(autor.getFirstName())
                .lastName(autor.getLastName())
                .build());
    }

    public void delete(Long id) {
        autorRepository.deleteById(id);
    }
}


