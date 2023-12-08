package com.mn.constant;

public enum NoticeKind {
    SYSTEM("시스템"), ANNOUNCEMENT("공지"), SERVICE("서비스"), EVENT("이벤트");

    private final String displayValue;
    NoticeKind(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue(){
        return displayValue;
    }
}
