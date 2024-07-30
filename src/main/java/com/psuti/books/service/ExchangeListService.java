package com.psuti.books.service;

import com.psuti.books.dto.ExchangeListDTO;
import com.psuti.books.model.*;
import com.psuti.books.repository.ExchangeListRepository;
import com.psuti.books.repository.OfferListRepository;
import com.psuti.books.repository.UserExchangeListRepository;
import com.psuti.books.repository.WishListRepository;
import com.psuti.books.security.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeListService {
    private OfferListRepository offerListRepository;
    private WishListRepository wishListRepository;
    private ExchangeListRepository exchangeListRepository;
    private UserExchangeListRepository userExchangeListRepository;

    public ResponseEntity<List<ExchangeList>> getIncoming(UserPrincipal principal) {
        return new ResponseEntity<>(exchangeListRepository.findIncoming(principal.getUserId()), HttpStatus.OK);
    }

    public ResponseEntity<List<ExchangeList>> getOutgoing(UserPrincipal principal) {
        return new ResponseEntity<>(exchangeListRepository.findOutgoing(principal.getUserId()), HttpStatus.OK);
    }

    public HttpStatus confirm(UserPrincipal principal, Long id) {
        if (!exchangeListRepository.findById(id).get().getWishList2().getUser().getId().equals(principal.getUserId()))
            return HttpStatus.FORBIDDEN;
        ExchangeList exchangeList = exchangeListRepository.findById(id).orElse(null);
        exchangeList.setBoth(true);
        exchangeList.setCreateAt(new Date());

        userExchangeListRepository.save(
                UserExchangeList.builder()
                        .exchangeList(exchangeList)
                        .offerlist(exchangeList.getOfferList2())
                        .receiving(false)
                        .build()
        );

        userExchangeListRepository.save(
                UserExchangeList.builder()
                        .exchangeList(exchangeList)
                        .offerlist(exchangeList.getOfferList1())
                        .receiving(false)
                        .build()
        );

        WishList wishList1 = exchangeList.getWishList1();
        wishList1.setArchived(true);
        wishListRepository.save(wishList1);
        WishList wishList2 = exchangeList.getWishList2();
        wishList2.setArchived(true);
        wishListRepository.save(wishList2);
        OfferList offerList1 = exchangeList.getOfferList1();
        offerList1.setArchived(true);
        offerListRepository.save(offerList1);
        OfferList offerList2 = exchangeList.getOfferList2();
        offerList2.setArchived(true);
        offerListRepository.save(offerList2);

        exchangeListRepository.save(exchangeList);

        return HttpStatus.OK;
    }

    public ResponseEntity<ExchangeList> create(UserPrincipal principal, ExchangeListDTO dto) {
        Long userid1 = principal.getUserId();
        Long userid2 = wishListRepository.findById(dto.getWishList2id()).get().getUser().getId();

        if(!userid2.equals(offerListRepository.findById(dto.getOfferList2id()).get().getUser().getId()))
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        WishList wishList1 = wishListRepository.findById(dto.getWishList1id()).orElse(null);
        OfferList offerList1 = offerListRepository.findById(dto.getOfferList1id()).orElse(null);
        WishList wishList2 = wishListRepository.findById(dto.getWishList2id()).orElse(null);
        OfferList offerList2 = offerListRepository.findById(dto.getOfferList2id()).orElse(null);

        if(wishList1.getUser().getId().equals(userid1) && wishList2.getUser().getId().equals(userid2)
        && offerList1.getUser().getId().equals(userid1) && offerList2.getUser().getId().equals(userid2)) {
            return new ResponseEntity<>(
                    exchangeListRepository.save(
                    ExchangeList.builder()
                            .wishList1(wishList1)
                            .offerList1(offerList1)
                            .wishList2(wishList2)
                            .offerList2(offerList2)
                            .createAt(new Date())
                            .isBoth(false)
                            .build())
                    , HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    public Object get(UserPrincipal principal) {
        List<OfferList> offers = offerListRepository.findByUserIdNot(principal.getUserId());

        List<OfferList> offers1 = offerListRepository.findByUserId(principal.getUserId());
        List<WishList> wishlists1 = wishListRepository.findByUserId(principal.getUserId());

        List<WishAndOffer> fullMatch = new ArrayList<>();
        List<WishAndOffer> partialMatch = new ArrayList<>();
        List<WishAndOffer> notMatch = new ArrayList<>();

        float percent;

        for (WishList wishList1 : wishlists1) {
            for (OfferList offerList2 : offers) {
                WishList wishList2 = wishListRepository.findByOfferListId(offerList2.getId());
                OfferList offerList1 = wishList1.getOfferList();
                if (wishList2.isArchived() || wishList1.isArchived() || offerList2.isArchived() || offerList1.isArchived())
                    continue;

                percent = (matchPercentage(wishList1, offerList2) + matchPercentage(wishList2, offerList1))/2;

                if (percent >= 100f) {
                    fullMatch.add(new WishAndOffer(wishList1, offerList1, wishList2, offerList2, percent));
                }
                if (percent > 0f && percent < 100f) {
                    partialMatch.add(new WishAndOffer(wishList1, offerList1, wishList2, offerList2, percent));
                }
                if (percent == 0f) {
                    notMatch.add(new WishAndOffer(wishList1, offerList1, wishList2, offerList2, percent));
                }
            }
        }

        Collections.sort(fullMatch);
        Collections.sort(partialMatch);
        Collections.sort(notMatch);

        return Offers.builder()
                .fullMatch(fullMatch)
                .partialMatch(partialMatch)
                .notMatch(notMatch)
                .build();
    }

    private float matchPercentage(WishList wishList, OfferList offerList) {
        int a = 0;
        for(Category category : wishList.getCategories()) {
            for (Category category1 : offerList.getCategories()) {
                if (category.equals(category1))
                    a++;
            }
        }
        return (float) a /wishList.getCategories().size() * 100;
    }
}

@AllArgsConstructor
@Getter
@Setter
@Builder
class Offers {
    List<WishAndOffer> fullMatch;
    List<WishAndOffer> partialMatch;
    List<WishAndOffer> notMatch;
}

@AllArgsConstructor
@Getter
@Setter
class WishAndOffer implements Comparable<WishAndOffer>{
    private WishList wishlist1;
    private OfferList offerList1;
    private WishList wishlist2;
    private OfferList offerList2;
    private float percentage;

    @Override
    public int compareTo(WishAndOffer o) {
        if (this.getPercentage() != o.getPercentage())
            return Float.compare(o.getPercentage(), this.getPercentage());
        else
            return Integer.compare(o.getOfferList2().getUser().getRating(), this.offerList2.getUser().getRating());
    }
}