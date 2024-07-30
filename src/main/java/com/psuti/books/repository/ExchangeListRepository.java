package com.psuti.books.repository;

import com.psuti.books.model.ExchangeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ExchangeListRepository extends JpaRepository<ExchangeList, Long> {
    @Query(value = "SELECT exchange_list.id, exchange_list.id_offer_list1, exchange_list.id_offer_list2,\n" +
            "exchange_list.id_wish_list1, exchange_list.id_wish_list2, exchange_list.create_at, exchange_list.is_both\n" +
            "FROM exchange_list\n" +
            "JOIN wish_list on wish_list.id = exchange_list.id_wish_list2\n" +
            "JOIN offer_list on offer_list.id = exchange_list.id_offer_list2\n" +
            "WHERE wish_list.id_user = :userId AND offer_list.id_user = :userId", nativeQuery = true)
    ArrayList<ExchangeList> findIncoming(@Param("userId") Long userId);

    @Query(value = "SELECT exchange_list.id, exchange_list.id_offer_list1, exchange_list.id_offer_list2,\n" +
            "exchange_list.id_wish_list1, exchange_list.id_wish_list2, exchange_list.create_at, exchange_list.is_both\n" +
            "FROM exchange_list\n" +
            "JOIN wish_list on wish_list.id = exchange_list.id_wish_list1\n" +
            "JOIN offer_list on offer_list.id = exchange_list.id_offer_list1\n" +
            "WHERE wish_list.id_user = :userId AND offer_list.id_user = :userId", nativeQuery = true)
    ArrayList<ExchangeList> findOutgoing(@Param("userId") Long userId);
}
