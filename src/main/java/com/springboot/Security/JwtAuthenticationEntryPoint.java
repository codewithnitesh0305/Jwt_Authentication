package com.springboot.Security;

import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	//It will show message if unauthorized person will try to access resource without authentication
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
			// TODO Auto-generated method stub
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			PrintWriter writer = response.getWriter();
			writer.println("Access Denied !! "+ authException.getMessage());
	        authException.getClass();

		}
}
