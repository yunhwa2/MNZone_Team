package com.mn.constant;

public enum PmAm {
    Pm("오후"),AM("오전");

    private final String displayValue;
    PmAm(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }


}
