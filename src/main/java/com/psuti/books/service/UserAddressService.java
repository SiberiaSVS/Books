package com.psuti.books.service;

import com.psuti.books.dto.UserAddressDTO;
import com.psuti.books.model.UserAddress;
import com.psuti.books.repository.UserAddressRepository;
import com.psuti.books.repository.UserRepository;
import com.psuti.books.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAddressService {
    private UserAddressRepository userAddressRepository;
    private UserRepository userRepository;
    public UserAddress create(UserAddressDTO dto, UserPrincipal principal) {
        return userAddressRepository.save(UserAddress.builder()
                .user(userRepository.findByEmail(principal.getEmail()))
                .addrIndex(dto.getAddrIndex())
                .addrCity(dto.getAddrCity())
                .addrStreet(dto.getAddrStreet())
                .addrHouse(dto.getAddrHouse())
                .AddrStructure(dto.getAddrStructure())
                .AddrApart(dto.getAddrApart())
                .isDefault(dto.isDefault())
                .build());
    }

    public List<UserAddress> getByUserPrincipal(UserPrincipal principal) {
        return userAddressRepository.findByUserId(userRepository.findByEmail(principal.getEmail()).getId());
    }

    public UserAddress getById(Long id) {
        return userAddressRepository.findById(id).orElse(null);
    }

    public UserAddress update(UserAddressDTO dto, UserPrincipal principal) {
        return userAddressRepository.save(UserAddress.builder()
                .id(dto.getId())
                .user(userRepository.findByEmail(principal.getEmail()))
                .addrIndex(dto.getAddrIndex())
                .addrCity(dto.getAddrCity())
                .addrStreet(dto.getAddrStreet())
                .addrHouse(dto.getAddrHouse())
                .AddrStructure(dto.getAddrStructure())
                .AddrApart(dto.getAddrApart())
                .isDefault(dto.isDefault())
                .build());
    }

    public void delete(Long id, UserPrincipal principal) {
        if (userRepository.findByEmail(principal.getEmail()) == userAddressRepository.findById(id).get().getUser())
            userAddressRepository.deleteById(id);
    }
}