package flab.delideli.dto;

import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberDto {

    @NotBlank
    private final String userid, password, username, phone;

    private final String address;

    @Builder
    public MemberDto(String userid, String password, String username, String phone, String address) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.address = address;
    }
}