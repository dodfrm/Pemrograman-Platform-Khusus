package com.polstat.perpustakaan.mapper;

import com.polstat.perpustakaan.dto.MemberDto;
import com.polstat.perpustakaan.entity.Member;

public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .memberID(member.getMemberID())
                .name(member.getName())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static Member mapToMember(MemberDto memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .memberID(memberDTO.getMemberID())
                .name(memberDTO.getName())
                .address(memberDTO.getAddress())
                .phoneNumber(memberDTO.getPhoneNumber())
                .build();
    }
}