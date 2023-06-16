package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.pojo.Organization;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

class OrganizationMapperTest extends SuperCloseReviewTest {
    @Resource
    private OrganizationMapper organizationMapper;

    @Test
    void insertSelective() {
        String[] organizations = new String[]{
                "Hubei Institute of Life Sciences",
                "South China Institute of Ecology",
                "South China Medical Research Institute",
                "China College of Traditional Chinese Medicine",
                "Giant Hard Research Institute",
                "Peanut industrial park",
                "Shaanxi Agricultural College",
                "Chongqing University of Posts and Telecommunications",
                "Beichuan University",
                "University of California",
                "Royal Electrotechnical College",
                "Libyan University of Automobile",
                "Agricultural University of Zambia",
                "The Brazilian Medical School",
                "Guangdong Computer University",
                "Old Occidental College",
                "American Free Shooting Academy",
                "Western School of Management"
        };

        Organization org;
        for (String name: organizations){
            org = new Organization();
            org.setName(name);
            organizationMapper.insertSelective(org);
        }

    }
}