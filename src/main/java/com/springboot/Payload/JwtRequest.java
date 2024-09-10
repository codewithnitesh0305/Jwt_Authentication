package com.springboot.Payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JwtRequest {

	private String email;
	private String password;
}
