package com.vincent.inc.Saturday.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "o_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ORole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String title;

    @Builder.Default
    @Column(columnDefinition = "BIT(1) default true")
    private boolean active = true;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Permission permission;
}
