package com.vincent.inc.Saturday.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Organization implements Serializable { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OUser> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ORole> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrganizationProfile organizationProfile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SMTP smtp;

    @JsonIgnore
    @Column(columnDefinition = "BIT(1) default false")
    private boolean disable = false;
}
