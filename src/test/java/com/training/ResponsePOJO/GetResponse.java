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
public class GetResponse {
	@JsonProperty(value = "id")
	public String id;
	
	@JsonProperty(value = "node_id")
	public String node_id;
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "full_name")
	public String full_name;
	
	@JsonProperty(value = "status")
	public String status;
	
	@JsonProperty(value = "message")
	public String message;
	
	
		
		
	
}
