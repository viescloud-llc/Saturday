package com.vincent.inc.Saturday.model.Authentication;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile implements Serializable
{
    private int id;
    
    private String alias;
    
    private String firstName;
    
    private String lastName;
    
    @JsonIgnore
    private String phoneNumber;
    
    private String email;
    
    @JsonIgnore
    private String address;
    
    private String city;

    private String state;
    
    private String zip;
}
