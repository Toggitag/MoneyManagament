package com.rk.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	private Set<String> resourceReq = new HashSet<>();

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<String> getResourceReq() {
		return resourceReq;
	}

	public void setResourceReq(Set<String> resourceReq) {
		this.resourceReq = resourceReq;
	}
}
