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
    
    @Column(columnDefinition = "BIT(1) default false")
    private boolean all = false;

    @Column(columnDefinition = "BIT(1) default true")
    private boolean read = true;
    
    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationUser = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationRole = false;

    @Column(columnDefinition = "BIT(1) default false")
    private boolean modifyOrganizationSmtp = false;
}
