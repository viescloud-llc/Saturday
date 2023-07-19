package com.vincent.inc.Saturday.model.Authentication;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vincent.inc.Saturday.model.TimeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable
{
    private int id;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;

    private UserProfile userProfile;

    @JsonIgnore
    private List<Role> userRoles;

    @JsonIgnore
    private boolean expirable = false;

    @JsonIgnore
    private TimeModel expireTime;

    @JsonIgnore
    private boolean enable = true;
}
