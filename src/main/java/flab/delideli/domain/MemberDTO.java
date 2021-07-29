package flab.delideli.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class MemberDTO {

    @NotBlank(message = "사용할 ID를 입력하세요.")
    private String userId;

    @NotBlank(message = "이름을 입력하세요.")
    private String userName;

    @NotBlank(message = "사용할 비밀번호를 입력하세요.")
    private String userPassword;

    @NotBlank(message = "전화번호를 입력하세요.")
    private String userPhone;

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
