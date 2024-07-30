package com.psuti.books.repository;

import com.psuti.books.model.UserExchangeList;
import com.psuti.books.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    @Query(value = "SELECT * FROM wish_list WHERE archived = false and id_user = :userId", nativeQuery = true)
    List<WishList> findByUserId(@Param("userId") Long userId);
    WishList findByOfferListId(Long offerListId);
}
