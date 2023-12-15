package com.mn.peter.dto;

import com.mn.constant.PmAm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter@Setter
@ToString
public class HospitalFormDTO {
    private String hospitalName;
    private PmAm openPmAm;
    private Integer openTimeHour;
    private Integer openTimeMin;
    private PmAm closePmAm;
    private Integer closeTimeHour;
    private Integer closeTimeMin;
    private String tel;
    private String hospitalAddress;
}
