package com.example.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallDetailsDto {
    // account number of customer (10 characters in length)
    private String accNum;
    private String aNum;
    private String bNum;
    //"P" = phone call, "S" = SMS, "M" = multimedia message
    private String callType;
    //integer value for cost of call in sen, e.g. 1, 2, 10
    private String callCost;

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getaNum() {
        return aNum;
    }

    public void setaNum(String aNum) {
        this.aNum = aNum;
    }

    public String getbNum() {
        return bNum;
    }

    public void setbNum(String bNum) {
        this.bNum = bNum;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallCost() {
        return callCost;
    }

    public void setCallCost(String callCost) {
        this.callCost = callCost;
    }
}
