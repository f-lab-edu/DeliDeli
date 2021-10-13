package flab.delideli.dto;

import flab.delideli.enums.UserLevel;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberDTO {

    @NotBlank
    private String userId;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPhone;
    @NotNull
    private UserLevel userLevel;

    private String userAddress;

    @Builder
    public MemberDTO(String userId, String userPassword, String userName, String userPhone, String userAddress, UserLevel userLevel) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userLevel = userLevel;
    }

}