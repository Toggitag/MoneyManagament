package com.rk.serviceimpl;

import com.rk.entity.Action;
import com.rk.entity.Resource;
import com.rk.repository.ResourceRepository;
import com.rk.request.ResourcesRequest;
import com.rk.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Boolean createResource(ResourcesRequest resourcesRequest) {
        Boolean isDone = true;
        try {
            Resource resource = new Resource();
            resource.setName(resourcesRequest.getResourceName());
            List<Action> actions = setActionList(resourcesRequest, resource);
            resource.setAction(actions);
            resourceRepository.save(resource);
        }
        catch (Exception ex){
            System.out.println("Error in create Resource");
             isDone = false;
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
