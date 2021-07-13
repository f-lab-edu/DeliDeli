package flab.delideli.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LoginDTO {

    @NotBlank(message = "아이디를 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String userPassword;

    public LoginDTO(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

}
