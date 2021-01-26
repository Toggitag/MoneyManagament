package com.rk.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "resource",uniqueConstraints = { @UniqueConstraint(columnNames = { "resourcename" })})
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "resourcename")
	private String resourcename;

	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Action> action = new ArrayList<Action>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return resourcename;
	}

	public void setName(String resourcename) {
		this.resourcename = resourcename;
	}

	public List<Action> getAction() {
		return action;
	}

	public void setAction(List<Action> action) {
		this.action = action;
	}

}
