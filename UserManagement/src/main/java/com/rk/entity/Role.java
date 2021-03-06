package com.rk.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "roles",uniqueConstraints = { @UniqueConstraint(columnNames = { "roleName" })})
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	
    @Column(length = 60)
    private String roleName;
    
    
    @Column(length = 100)
    private String roleDescription;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_resource", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
	private Set<Resource> resource = new HashSet<>();
    
    public Role() {
	}
    
    public Role(String roleName) {
        this.roleName = roleName;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<Resource> getResource() {
		return resource;
	}

	public void setResource(Set<Resource> resource) {
		this.resource = resource;
	}
	
    
}
