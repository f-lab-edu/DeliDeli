package flab.delideli.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class ResponseEntityCode {

	public static final ResponseEntity<Void> OK_RESPONSE_ENTITY =
		new ResponseEntity<>(HttpStatus.OK);
	public static final ResponseEntity<Void> CONFLICT_RESPONSE_ENTITY =
		new ResponseEntity<>(HttpStatus.CONFLICT);
	public static final ResponseEntity<Void> UNAUTHORIZED_RESPONSE_ENTITY =
		new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	public static final ResponseEntity FORBIDDEN_RESPONSE_ENTITY = new ResponseEntity(HttpStatus.FORBIDDEN);
}
