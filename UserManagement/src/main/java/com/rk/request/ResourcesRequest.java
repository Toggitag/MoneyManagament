package com.rk.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourcesRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 10, max = 10)
	@JsonProperty("resourceName")
	private String resourceName;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@JsonProperty("actions")
	private List<ActionsRequest> actionRequest = new ArrayList<>();

	public List<ActionsRequest> getActionRequest() {
		return actionRequest;
	}

	public void setActionRequest(List<ActionsRequest> actionRequest) {
		this.actionRequest = actionRequest;
	}
	
	
}
