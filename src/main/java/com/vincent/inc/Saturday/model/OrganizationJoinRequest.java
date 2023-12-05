package com.vincent.inc.Saturday.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "organization_join_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinRequest implements Serializable {
    @Id
    private int id;

    @Column
    private int userId;
    
    @Column
    private String organizationId;

    @Column
    private String message;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TimeModel timeCreated; 
}
