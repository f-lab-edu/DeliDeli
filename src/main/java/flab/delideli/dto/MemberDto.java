package flab.delideli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String userid;
    private String password;
    private String username;
    private String phone;
    private String address;

    public MemberDto() {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.address = address;
    }
}