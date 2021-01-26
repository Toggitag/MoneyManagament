package com.rk.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rk.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.entity.Action;
import com.rk.entity.Resource;
import com.rk.entity.Role;
import com.rk.repository.RoleRepository;
import com.rk.request.ResourcesRequest;
import com.rk.request.RoleRequest;
import com.rk.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

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
			List<Resource> resourceList = resourceRepository.findAll();
			Set<Resource> resourcesSet = new HashSet<Resource>();
			roleRequest.getResourceReq().forEach(resourceReq -> {
				resourceList.forEach( resource ->  {
					if(roleRequest.getRoleName().equals(resource.getName()))
						resourcesSet.add(resource);
				});
			});
			role.setResource(resourcesSet);
			roleRepository.save(role);
			isDone = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return isDone;
	}
}
