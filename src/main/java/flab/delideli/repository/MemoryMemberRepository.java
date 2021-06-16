package flab.delideli.repository;

import flab.delideli.domain.MemberDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberDTO> store = new ConcurrentHashMap<>();

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        store.put(memberDTO.getId(), memberDTO);
        return memberDTO;
    }

    public void clearStore() {
        store.clear();
    }

}
