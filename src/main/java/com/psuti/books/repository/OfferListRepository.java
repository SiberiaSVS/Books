package com.psuti.books.repository;

import com.psuti.books.model.OfferList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferListRepository extends JpaRepository<OfferList, Long> {
    @Query(value = "SELECT * FROM offer_list WHERE archived = false and id_user <> :userId", nativeQuery = true)
    List<OfferList> findByUserIdNot(@Param("userId") Long userId);
    @Query(value = "SELECT * FROM offer_list WHERE archived = false and id_user = :userId", nativeQuery = true)
    List<OfferList> findByUserId(@Param("userId") Long userId);
}
