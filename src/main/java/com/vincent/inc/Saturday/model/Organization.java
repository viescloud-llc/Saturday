package com.vincent.inc.Saturday.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany
    private List<EUser> user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ERole> roles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrganizationProfile organizationProfile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SMTP smtp;
}
