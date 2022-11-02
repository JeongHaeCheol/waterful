package com.example.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NonNull
	@Size(min = 3, max = 30)
	private String username;

	@NonNull
	@Size(min = 3, max = 20)
	private String password;
}
