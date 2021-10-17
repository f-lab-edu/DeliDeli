package flab.delideli.service;

import flab.delideli.dao.MemberDao;
import flab.delideli.dto.MemberDTO;
import flab.delideli.dto.UpdateDTO;
import flab.delideli.encrypt.EncryptSha256;
import flab.delideli.enums.UserLevel;
import flab.delideli.exception.DuplicatedIdException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final EncryptSha256 encryptPassword;

    @Override
    public void joinMember(MemberDTO member) {

        if(memberDao.isExistUserId(member.getUserId())) {
            throw new DuplicatedIdException("이미 존재하는 아이디입니다.");
        }

        MemberDTO encodeMember = new MemberDTO(member.getUserId(), encryptPassword.encrypt(member.getUserPassword()),
            member.getUserName(), member.getUserPhone(), member.getUserAddress(), member.getUserLevel());

        memberDao.joinMember(encodeMember);

        if(member.getUserLevel() == UserLevel.OWNER_LEVEL) {
            memberDao.insertOwnerId(member.getUserId());
        }

    }

    @Override
    public void duplicatedId(String userId) {
        if(memberDao.isExistUserId(userId)) {
            throw new DuplicatedIdException("이미 존재하는 아이디입니다.");
        }
    }

    @Override
    public void setOwnerDocsSubmission(String ownerId) {
        memberDao.updateOwnerDocsSubmission(ownerId);
    }

    @Override
    public void setOwnerLoginApproval(String ownerId) {
        memberDao.updateOwnerLoginApproval(ownerId);
    }

    @Override
    public void updateUserInfo(String userId, UpdateDTO updateDTO) {
        memberDao.updateUser(userId, updateDTO);
    }

    @Override
    public void deleteUserInfo(String userId) {
        memberDao.deleteUser(userId);
    }

}