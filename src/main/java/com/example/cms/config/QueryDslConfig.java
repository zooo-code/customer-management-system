package com.example.cms.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Querydsl 을 사용하기 위해서는 JPAQueryFactory 가 필요한데 Querydsl 을 사용하는 Repository 에서 주입받으려면 불편합니다.
 * 따라서 Config 를 생성하여 JPAQueryFactory 를 bean 으롤 등록하고 사용할 Repository 에서 생성자 주입을 받습니다.
 */
@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager em;
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }

}
