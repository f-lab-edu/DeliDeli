package flab.delideli.repository;

import flab.delideli.domain.MemberDTO;

import java.util.List;

public interface MemberDAO {
    List<MemberDTO> selectMembers(MemberDTO param) throws Exception;
}
