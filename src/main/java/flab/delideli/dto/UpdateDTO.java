package flab.delideli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {

    @Nullable
    private String username;
    @Nullable
    private String phone;
    @Nullable
    private String address;
}
