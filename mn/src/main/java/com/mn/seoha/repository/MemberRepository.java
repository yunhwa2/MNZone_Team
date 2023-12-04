package com.mn.seoha.repository;

import com.mn.entity.Member;
import com.querydsl.apt.jpa.JPAConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findById(String id);
}
