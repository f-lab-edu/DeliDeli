package flab.delideli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class UpdateDTO {

    @Nullable
    private String username;
    @Nullable
    private String phone;
    @Nullable
    private String address;
}
