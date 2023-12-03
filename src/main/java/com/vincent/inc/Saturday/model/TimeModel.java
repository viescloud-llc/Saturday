package com.vincent.inc.Saturday.model;

import java.io.Serializable;

import com.vincent.inc.viesspringutils.util.Time;

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
@Table(name = "time_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeModel extends Time implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private int year;
    
    @Column
    private int month;
    
    @Column
    private int day;
    
    @Column
    private int hours;
    
    @Column
    private int minute;
    
    @Column
    private int second;

    @Column(length = 100)
    private String status; //start or end
}
