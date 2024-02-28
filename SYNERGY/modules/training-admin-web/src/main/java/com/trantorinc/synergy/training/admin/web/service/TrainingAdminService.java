package com.trantorinc.synergy.training.admin.web.service;


import javax.portlet.ResourceResponse;
import java.io.IOException;

public interface TrainingAdminService {

    void exportEmployee(ResourceResponse response) throws IOException;
}
