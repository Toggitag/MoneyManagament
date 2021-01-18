package com.rk.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.entity.Action;
import com.rk.entity.Resource;
import com.rk.entity.Role;
import com.rk.repository.ResourceRepository;
import com.rk.repository.RoleRepository;
import com.rk.request.ResourcesRequest;
import com.rk.request.RoleRequest;
import com.rk.service.RoleService;

@Service
public class RoleManagementServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ResourceRepository resourceRepository;
	@Override
	public Boolean saveRole(RoleRequest roleRequest) {
		boolean isDone = false;
		try {	
			Role role = new Role();
			role.setRoleName(roleRequest.getRoleName());
			role.setRoleDescription(roleRequest.getRoleDescription());
			List<Resource> findAllResources = resourceRepository.findAll();
			
			Set<Resource> resourcesSet = new HashSet<Resource>();
			roleRequest.getResourceReq().forEach(resourceReq -> {
				findAllResources.forEach(findAllResource -> {
					if(findAllResource.getName().equals(resourceReq.getResourceName())) {
						resourcesSet.add(findAllResource);
					}
				});
				role.setResource(resourcesSet);
			});
			
			roleRepository.save(role);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}							
		return isDone;
	}

	private List<Action> setActionList(ResourcesRequest resourceReq, Resource resource) {

		List<Action> actions = new ArrayList<Action>();
		resourceReq.getActionRequest().forEach(actionReq -> {
			if (Boolean.TRUE.equals(actionReq.getAdd())) {
				Action actionAdd = new Action();
				actionAdd.setName(actionReq.getActionName());
				actionAdd.setGroupName(actionReq.getGroupName());
				actionAdd.setType("add");
				actionAdd.setResource(resource);
				actions.add(actionAdd);
			}
			if (Boolean.TRUE.equals(actionReq.getView())) {
				Action actionView = new Action();
				actionView.setName(actionReq.getActionName());
				actionView.setGroupName(actionReq.getGroupName());
				actionView.setType("view");
				actionView.setResource(resource);
				actions.add(actionView);
			}

			if (Boolean.TRUE.equals(actionReq.getModify())) {
				Action actionModify = new Action();
				actionModify.setName(actionReq.getActionName());
				actionModify.setGroupName(actionReq.getGroupName());
				actionModify.setType("modify");
				actionModify.setResource(resource);
				actions.add(actionModify);
			}

			if (Boolean.TRUE.equals(actionReq.getDelete())) {
				Action actionDelete = new Action();
				actionDelete.setName(actionReq.getActionName());
				actionDelete.setGroupName(actionReq.getGroupName());
				actionDelete.setType("delete");
				actionDelete.setResource(resource);
				actions.add(actionDelete);
			}
		});
		return actions;
	}
}
