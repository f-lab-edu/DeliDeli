package flab.delideli.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

//request 용도로만 사용하는 클래스이므로 'is never assigned' 경고를 무시합니다.
@SuppressWarnings("unused")
@NoArgsConstructor
@Getter
public class RequestLoginDTO {

    @NotBlank(message = "아이디를 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String userPassword;

}
