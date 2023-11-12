package com.example.cms.initdb;



import com.example.cms.member.domain.EMemberStatus;
import com.example.cms.member.infrastructure.MemberEntity;
import com.example.cms.member.infrastructure.MemberRepositoryJpa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {


    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();

    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberRepositoryJpa memberRepository;

        public void dbInit1(){
            List<MemberEntity> memberEntities = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                MemberEntity entity = MemberEntity.builder()
                        .name("test" +i)
                        .status(EMemberStatus.OPEN)
                        .membershipPoint(100+i*10)
                        .mobile("1234" + i)
                        .build();
                memberEntities.add(entity);
            }

            memberRepository.saveAll(memberEntities);


        }

    }


}
