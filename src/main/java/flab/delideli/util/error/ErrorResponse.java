package flab.delideli.util.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private String message;

    public ErrorResponse(StatusCode code) {
        this.message = code.getMessage();
    }

    public static ErrorResponse of(StatusCode code) {
        return new ErrorResponse(code);
    }

}
