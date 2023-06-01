package com.beta.closereview.service.impl;

import com.beta.closereview.mapper.OrganizationMapper;
import com.beta.closereview.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public String getOrganizationNameById(Integer oid) {
        return organizationMapper.getOrganizationNameById(oid);
    }
}
