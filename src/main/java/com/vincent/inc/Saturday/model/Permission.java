package com.vincent.inc.Saturday.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "all_permission",columnDefinition = "BIT(1) default false")
    private boolean all = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean readOrganizationUser = false;

    @Column(columnDefinition = "BIT(1) default true")
    private boolean readOrganizationRole = true;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean readOrganizationProfile = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean readOrganizationSmtp = false;
    
    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationUser = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationRole = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationProfile = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationSmtp = false;
}
