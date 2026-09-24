package com.example.spring01.repository;

import com.example.spring01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByAccount(String account);
    List<Member> findByAccount(String account);
}
