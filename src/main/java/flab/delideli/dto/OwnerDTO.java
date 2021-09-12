package flab.delideli.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerDTO {

	@NotBlank
	private String ownerId;
	@NotBlank
	private String ownerPassword;
	@NotBlank
	private String ownerName;
	@NotBlank
	private String ownerPhone;
	@NotBlank
	private String ownerAddress;

	@Builder
	public OwnerDTO(String ownerId, String ownerPassword, String ownerName, String ownerPhone,
		String ownerAddress) {
		this.ownerId = ownerId;
		this.ownerPassword = ownerPassword;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.ownerAddress = ownerAddress;
	}

}
