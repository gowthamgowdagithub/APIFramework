package com.training.ResponsePOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CreateNewRepoResponse {
	@JsonProperty(value="status")
	public String status;
	
	@JsonProperty(value="name")
	public String name;
	
	@JsonProperty(value="login")
	public String login;
	
	@JsonProperty(value="type")
	public String type;
}
