package com.example.spring01.service;

import com.example.spring01.entity.Member;
import com.example.spring01.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    public boolean checkAccount(String account){
        return repository.existsByAccount(account);
    }

    public boolean register(Member member){

        if (!checkAccount(member.getAccount())) {

            member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
            Member saveMember = repository.save(member);
            return saveMember != null;
        }else{
            return false;
        }
    }

}
