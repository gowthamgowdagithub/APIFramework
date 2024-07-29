package com.training.RequestPOJO;

public class CreateNewRepoRequest {


	public String name;
	public String description;
	public String homepage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getPrivat() {
		return privat;
	}
	public void setPrivat(String privat) {
		this.privat = privat;
	}
	public String privat;
}
