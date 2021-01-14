package com.rk.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionsRequest {

	@JsonProperty("actionName")
	private String actionName;

	@JsonProperty("groupName")
	private String groupName;
	
	@JsonProperty("add")
	private Boolean add;
	@JsonProperty("view")
	private Boolean view;
	@JsonProperty("modify")
	private Boolean modify;
	@JsonProperty("delete")
	private Boolean delete;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Boolean getAdd() {
		return add;
	}
	public void setAdd(Boolean add) {
		this.add = add;
	}
	public Boolean getView() {
		return view;
	}
	public void setView(Boolean view) {
		this.view = view;
	}
	public Boolean getModify() {
		return modify;
	}
	public void setModify(Boolean modify) {
		this.modify = modify;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
