package com.vincent.inc.Saturday.model.Authentication;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable
{
    private int id;

    private String name;

    private int level;
}
