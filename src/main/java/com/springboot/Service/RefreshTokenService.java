package com.springboot.Service;

import com.springboot.Model.RefreshToken;

public interface RefreshTokenService {

	public RefreshToken createRefershToken(String email);
	public RefreshToken verifyRefershToken(String refershToken);
}
