package com.vincent.inc.Saturday.model.Authentication;

import java.io.Serializable;
import java.util.List;

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

    private String username;

    private String password;

    private UserProfile userProfile;

    private List<Role> userRoles;

    private boolean expirable = false;

    private TimeModel expireTime;

    private boolean enable = true;
}
