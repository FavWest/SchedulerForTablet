package com.example.scheduler;

public class ChildSchedule {
    private String monValue;
    private String tuesValue;

    public ChildSchedule(String monValue, String tuesValue){
        this.monValue = monValue;
        this.tuesValue = tuesValue;
    }

    public String getMonValue(){
        return monValue;
    }

    public String getTuesValue(){
        return tuesValue;
    }

    public void setMonValue(String monValue){
        this.monValue = monValue;
    }

    public void setTuesValue(String tuesValue){
        this.tuesValue = tuesValue;
    }
}
