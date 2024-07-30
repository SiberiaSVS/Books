package com.psuti.books.repository;

import com.psuti.books.model.UserExchangeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserExchangeListRepository extends JpaRepository<UserExchangeList, Long> {
    @Query(value = "SELECT user_exchange_list.id, user_exchange_list.receiving , user_exchange_list.track_number,\n" +
            "user_exchange_list.id_exchange_list, user_exchange_list.id_offer_list\n" +
            "FROM user_exchange_list\n" +
            "JOIN offer_list ON user_exchange_list.id_offer_list = offer_list.id\n" +
            "WHERE offer_list.id_user = :userId", nativeQuery = true)
    List<UserExchangeList> findByUserId(@Param("userId") Long userId);

    List<UserExchangeList> findByExchangeListId(Long id);
}
