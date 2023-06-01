package com.beta.closereview.service;

import com.beta.closereview.dao.Organization;

import java.util.List;

public interface OrganizationService {
    String getOrganizationNameById(Integer oid);

    List<Organization> getOrganizations();
}
