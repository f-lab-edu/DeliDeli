package flab.delideli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PushDTO {

	String title;

	String message;

	public PushDTO(String title, String message) {
		this.title = title;
		this.message = message;
	}

}