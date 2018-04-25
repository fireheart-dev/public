package entrance.web.controller;

import entrance.web.dto.ApplicationDto;
import entrance.service.api.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Api(value = "Provides management of applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = RequestMethod.GET, value = "/recent/{contactId}")
    @ApiOperation(value = "returns most recent application by provided contactId")
    public ApplicationDto getRecentByContactId(@PathVariable Long contactId) {
        return applicationService.getRecentByContactId(contactId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listByProductName/{productName}")
    @ApiOperation(value = "returns list of applications by provided productName")
    public List<ApplicationDto> findByProductName(@PathVariable String productName) {
        return applicationService.findByProductName(productName);
    }
}
