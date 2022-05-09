package flab.delideli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartDTO {

	List<CartlistDTO> cartlistDTOS;
	long totalPrice;
}
