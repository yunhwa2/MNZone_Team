package com.mn.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {
    private Long hospitalName;
}
