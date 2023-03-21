package com.base.project.tdd.domain.member.repository;


import com.base.project.tdd.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
