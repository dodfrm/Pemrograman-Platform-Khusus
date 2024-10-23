package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(@Param("name") String name);

    List<Member> findByMemberID(@Param("member_id") String memberID);
}