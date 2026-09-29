package com.example.spring01.controller;

import com.example.spring01.entity.Gift;
import com.example.spring01.repository.GiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/gifts")
@RestController
public class GiftController {
    @Autowired
    private GiftRepo repo;

    @GetMapping("")
    public void queryGiftByPage(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int rpp){
        Pageable pageable = PageRequest.of(page, rpp);
        Page<Gift> giftPage = repo.findAll(pageable);

        List<Gift> giftList = giftPage.getContent();
        for (Gift gift : giftList){
            System.out.println(gift.getName());
        }

    }


}
