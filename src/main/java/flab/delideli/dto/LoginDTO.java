package flab.delideli.dto;

import flab.delideli.annotation.LoginUserLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {

    private String loginid;

    private String loginPassword;

    private LoginUserLevel loginUserLevel;

}
