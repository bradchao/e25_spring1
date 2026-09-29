package com.example.spring01.repository;

import com.example.spring01.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepo extends JpaRepository<Gift,Long> {
}
