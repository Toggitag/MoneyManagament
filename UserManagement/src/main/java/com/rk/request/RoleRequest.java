package com.rk.request;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
    @Size(min = 3, max = 50)
	@JsonProperty("rolename")
    private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@NotBlank
    @Size(min = 10, max = 10)
	@JsonProperty("roleDescription")
    private String roleDescription;
	
	@JsonProperty("resources")
	private Set<ResourcesRequest> resourceReq = new HashSet<>();

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<ResourcesRequest> getResourceReq() {
		return resourceReq;
	}

	public void setResourceReq(Set<ResourcesRequest> resourceReq) {
		this.resourceReq = resourceReq;
	}
	
	
}
