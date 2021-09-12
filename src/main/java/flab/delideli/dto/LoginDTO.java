package flab.delideli.dto;

import flab.delideli.enums.UserLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {

    private String loginid;

    private String loginPassword;

    private UserLevel userLevel;

}
