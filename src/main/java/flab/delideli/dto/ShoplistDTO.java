package flab.delideli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoplistDTO {

    @NotBlank
    private String shopName;
    @NotBlank
    private String shopTel;
    @NotBlank
    private String shopLocation;
    @NotBlank
    private String deliveryArea;
    @NotBlank
    private int shopId;
}
