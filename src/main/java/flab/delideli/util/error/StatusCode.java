package flab.delideli.util.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusCode {

    CONFLICT_USERID("중복된 아이디입니다."),
    OK_USERID("사용 가능한 아이디입니다."),
    UNAUTHORIZED_LOGIN("아이디 또는 비밀번호가 일치하지 않습니다."),
    OK_LOGIN("환영합니다.");

    private String message;

    StatusCode(String message) {
        this.message = message;
    }

}
