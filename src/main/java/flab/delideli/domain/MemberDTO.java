package flab.delideli.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberDTO {

    @NotBlank
    private String userId, userName, userPassword, userPhone;

    private String userAddress;

    @Builder
    public MemberDTO(String userId, String userName,
                     String userPassword, String userPhone, String userAddress) {

        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;

    }

}
