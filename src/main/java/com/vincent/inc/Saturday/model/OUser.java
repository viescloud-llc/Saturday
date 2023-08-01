package com.vincent.inc.Saturday.model;

import java.util.ArrayList;
import java.util.List;

import com.vincent.inc.Saturday.model.Authentication.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "o_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OUser extends User {

    @Id
    private int id;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<ORole> defineRole = new ArrayList<>();
}
