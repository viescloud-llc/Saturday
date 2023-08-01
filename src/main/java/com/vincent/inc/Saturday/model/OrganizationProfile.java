package com.vincent.inc.Saturday.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "organization_profile")
@Data
@AllArgsConstructor
public class OrganizationProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 100)
    private String publicEmail;
    
    @Column(length = 500)
    private String bio;
    
    @Column(length = 50)
    private String timeZone;
    
    @Column
    private List<String> socialMedias;

    @Column(length = 100)
    private String name;
    
    @Column(length = 100)
    private String address;
    
    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;
    
    @Column
    private int zip;

    public OrganizationProfile() {
        this.socialMedias = new ArrayList<>();
    }
}
