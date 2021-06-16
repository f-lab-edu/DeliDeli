package flab.delideli.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String phone;
    private String address;

    public MemberDTO(Long id, String userId, String name, String password, String phone, String address) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
