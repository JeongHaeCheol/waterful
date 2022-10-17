package com.example.Dto;

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
public class LoginDto {

	@NonNull
	@Size(min = 3, max = 50)
	private String username;

	@NonNull
	@Size(min = 3, max = 100)
	private String password;
}
