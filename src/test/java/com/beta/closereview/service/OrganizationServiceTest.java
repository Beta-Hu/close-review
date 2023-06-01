package com.beta.closereview.service;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.dao.Organization;
import com.beta.closereview.mapper.OrganizationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class OrganizationServiceTest extends SuperCloseReviewTest {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Test
    void getOrganizationNameById() {
    }

    @Test
    void getOrganizations() {
        List<Organization> organizations = organizationMapper.getOrganizations();
        for (Organization organization : organizations) {
            System.out.println(organization);
        }
    }
}