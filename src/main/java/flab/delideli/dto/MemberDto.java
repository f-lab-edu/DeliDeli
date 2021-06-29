package flab.delideli.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberDto {

    @NotBlank
    private String userid;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    @NotBlank
    private String phone;

    private String address;

    @Builder
    public MemberDto(String userid, String password, String username, String phone, String address) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}