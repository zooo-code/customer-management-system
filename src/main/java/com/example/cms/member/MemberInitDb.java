package com.example.cms.member;

import com.example.cms.item.controller.request.ItemCreateRequest;
import com.example.cms.item.domain.ItemStatus;
import com.example.cms.item.service.ItemService;
import com.example.cms.member.controller.request.MemberCreateRequest;
import com.example.cms.member.domain.MemberStatus;
import com.example.cms.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class MemberInitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();

    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final ItemService itemService;
        public void dbInit1(){
            for(int i = 0; i<100; i++){
                MemberCreateRequest memberCreateRequest = new MemberCreateRequest("1234" + i,"kim" +i ,MemberStatus.OPEN);
                memberService.save(memberCreateRequest);
            }
            ItemCreateRequest ice = new ItemCreateRequest("라떼", 1000, ItemStatus.ICED);
            ItemCreateRequest hot = new ItemCreateRequest("아메리카노", 2000, ItemStatus.HOT);
            itemService.create(ice);
            itemService.create(hot);
        }
    }
}
