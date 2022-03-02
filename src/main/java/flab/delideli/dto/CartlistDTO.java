package flab.delideli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartlistDTO {

    private int cartId;
    private int userId;
    private int menuId;
    private String menuName;
    private int price;
    private int amount;
    private int totalPrice;
}
