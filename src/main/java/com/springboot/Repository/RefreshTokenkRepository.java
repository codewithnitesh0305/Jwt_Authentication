package com.springboot.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Model.RefreshToken;



@Repository
public interface RefreshTokenkRepository extends JpaRepository<RefreshToken, String>{

	 Optional<RefreshToken> findByRefreshToken(String refershToken);
}
