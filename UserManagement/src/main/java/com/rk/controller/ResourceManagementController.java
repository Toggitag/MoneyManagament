package com.rk.controller;

import com.rk.constants.EndPointsConstants;
import com.rk.repository.ResourceRepository;
import com.rk.request.ResourcesRequest;
import com.rk.response.ResponseMessage;
import com.rk.service.ResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPointsConstants.RESOURCE_MANAGEMENT)
public class ResourceManagementController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    ResourceRepository resourceRepository;
    private  static  final Logger logger = LogManager.getLogger(ResourceManagementController.class);
    @PostMapping("/save_resource")
    public ResponseEntity<Object> saveResource(@Valid @RequestBody ResourcesRequest resourcesRequest) {
        logger.info("save Resource Controller started");
        Long startTime = System.currentTimeMillis();
        if (Boolean.TRUE.equals(resourceRepository.existsByResourcename(resourcesRequest.getResourceName()))) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Rolename is already taken!"),
                    HttpStatus.CONFLICT);
        }

        Boolean resource = resourceService.createResource(resourcesRequest);
        if (Boolean.FALSE.equals(resource)) {
            return new ResponseEntity<>(new ResponseMessage("Resource Not Created Successfully!"),
                    HttpStatus.NOT_IMPLEMENTED);
        }
        Long endTime = System.currentTimeMillis();
        logger.info(String.format("save Resource Controller ended and took %d ", endTime-startTime));
        return new ResponseEntity<>(new ResponseMessage("Resource Created Successfully!"),
                HttpStatus.OK);
        }

}
