package com.psuti.books.service;


import com.psuti.books.model.UserExchangeList;
import com.psuti.books.repository.ExchangeListRepository;
import com.psuti.books.repository.UserExchangeListRepository;
import com.psuti.books.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserExchangeListService {
    private UserExchangeListRepository userExchangeListRepository;
    private ExchangeListRepository exchangeListRepository;

    public ResponseEntity<UserExchangeList> updateTrackNumber(UserPrincipal principal, String trackNumber, Long id) {
        UserExchangeList userExchangeList = userExchangeListRepository.findById(id).orElse(null);
        if(userExchangeList.getOfferlist().getUser().getId().equals(principal.getUserId())) {
            userExchangeList.setTrackNumber(trackNumber);
            return new ResponseEntity<>(userExchangeListRepository.save(userExchangeList), HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<UserExchangeList> setReceiving(UserPrincipal principal, Long id) {
        UserExchangeList userExchangeList = userExchangeListRepository.findById(id).orElse(null);
        if(userExchangeList.getOfferlist().getUser().getId().equals(principal.getUserId())) {
            userExchangeList.setReceiving(true);
            return new ResponseEntity<>(userExchangeListRepository.save(userExchangeList), HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<UserExchangeList> getUserExchangeList(UserPrincipal principal, Long id) {
        UserExchangeList userExchangeList = userExchangeListRepository.findById(id).orElse(null);
        if(userExchangeList.getOfferlist().getUser().getId().equals(principal.getUserId())) {
            return new ResponseEntity<>(userExchangeList, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<List<UserExchangeList>> getUserExchangeLists(UserPrincipal principal) {
        return new ResponseEntity<>(userExchangeListRepository.findByUserId(principal.getUserId()).stream().filter(a ->
                !a.isReceiving()).collect(Collectors.toList())
                , HttpStatus.OK);
    }

    public ResponseEntity<List<UserExchangeList>> getArchivedUserExchangeLists(UserPrincipal principal) {
        return new ResponseEntity<>(userExchangeListRepository.findByUserId(principal.getUserId()).stream().filter(a ->
                a.isReceiving()).collect(Collectors.toList())
                , HttpStatus.OK);
    }
}
