package com.polstat.perpustakaan.service;

import java.util.List;

import com.polstat.perpustakaan.dto.MemberDto;

public interface MemberService {
    public MemberDto createMember(MemberDto memberDto);

    public MemberDto updateMember(MemberDto memberDto);

    public void deleteMember(MemberDto memberDto);

    public List<MemberDto> getMembers();

    public MemberDto getMember(Long id);

    public List<MemberDto> findMemberByName(String name);
}
