package com.vincent.inc.Saturday.model.Authentication;

import java.io.Serializable;
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
    
    private String phoneNumber;
    
    private String email;
    
    private String address;
    
    private String city;

    private String state;
    
    private String zip;
}
