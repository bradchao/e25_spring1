package com.example.spring01.service;

import com.example.spring01.entity.Member;
import com.example.spring01.repository.MemberRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean login(String account, String passwd){
        Member member = repository.findByAccount(account).orElse(null);
        if (member != null && BCrypt.checkpw(passwd, member.getPasswd())){
            return true;
        }
        return false;
    }

    public boolean loginV2(String account, String passwd){
        Member member = new Member();
        member.setAccount(account);
        Example<Member> example = Example.of(member);
        if (repository.exists(example)) {   // SELECT * FROM member WHERE account = xxx
            List<Member> members = repository.findAll(example);
            Member dbMember = members.get(0);
            if (BCrypt.checkpw(passwd, dbMember.getPasswd())){
                return true;
            }
        }
        return false;
    }

    public Member loginV3(String account, String passwd){
        Member member = repository.findByAccount(account).orElse(null);
        if (member != null && BCrypt.checkpw(passwd, member.getPasswd())){
            return member;
        }
        return null;
    }


}
