package com.psuti.books.service;

import com.psuti.books.dto.WishListDTO;
import com.psuti.books.model.Category;
import com.psuti.books.model.WishList;
import com.psuti.books.repository.*;
import com.psuti.books.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class WishListService {
    private WishListRepository wishListRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;
    private UserAddressRepository userAddressRepository;
    private OfferListRepository offerListRepository;
    private CategoryRepository categoryRepository;
    public WishList create(WishListDTO dto, UserPrincipal principal) {
        List<Category> categoryFromDTO = new ArrayList<>();

        for (Long id : dto.getCategories()) {
            categoryFromDTO.add(categoryRepository.findById(id).orElse(null));
        }
        return wishListRepository.save(WishList.builder()
                .user(userRepository.findByEmail(principal.getEmail()))
                .createAt(new Date())
                .updateAt(new Date())
                .status(statusRepository.findById(1L).orElse(null))
                .userAddress(userAddressRepository.findById(dto.getUserAddressId()).orElse(null))
                .categories(categoryFromDTO)
                .offerList(offerListRepository.findById(dto.getOfferListId()).orElse(null))
                .build());
    }

    public List<WishList> getAll(UserPrincipal principal) {
        return wishListRepository.findByUserId(userRepository.findByEmail(principal.getEmail()).getId());
    }

    public WishList getById(Long id) {
        return wishListRepository.findById(id).orElse(null);
    }

//    public WishList update(WishListDTO dto) {
//        return wishListRepository.save(WishList.builder()
//                .id(dto.getId())
//                .user(userRepository.findById(dto.getUserId()).orElse(null))
//                .status(statusRepository.findById(dto.getStatusId()).orElse(null))
//                .userAddress(userAddressRepository.findById(dto.getUserAddressId()).orElse(null))
//                .build());
//    }

    public HttpStatus delete(Long id, UserPrincipal principal) {
        if (userRepository.findByEmail(principal.getEmail()) == wishListRepository.findById(id).get().getUser()) {
            wishListRepository.deleteById(id);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.FORBIDDEN;
        }
    }
}
