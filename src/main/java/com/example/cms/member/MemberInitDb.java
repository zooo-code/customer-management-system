package com.example.cms.member;

import com.example.cms.item.service.ItemServiceImpl;
import com.example.cms.member.service.MemberServiceImpl;
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
        private final MemberServiceImpl memberServiceImpl;
        private final ItemServiceImpl itemServiceImpl;
        public void dbInit1(){

        }
    }
}
