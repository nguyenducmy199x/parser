package com.example.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallDetailsReportDto {
    // Total cost of calls for each account number in the file
    private Map<String, Integer> totalCostOfCallsForEachAccNum;
    //Total cost of calls for each call type in the file
    private String totalCostOfCallsForCallTypeP;
    private String totalCostOfCallsForCallTypeS;
    private String totalCostOfCallsForCallTypeM;
    //Total cost of calls for each day in the file (the day the call started)
    private String totalCostOfCallOfADay;

    public String getTotalCostOfCallOfADay() {
        return totalCostOfCallOfADay;
    }

    public void setTotalCostOfCallOfADay(String totalCostOfCallOfADay) {
        this.totalCostOfCallOfADay = totalCostOfCallOfADay;
    }

    public String getTotalCostOfCallsForCallTypeM() {
        return totalCostOfCallsForCallTypeM;
    }

    public void setTotalCostOfCallsForCallTypeM(String totalCostOfCallsForCallTypeM) {
        this.totalCostOfCallsForCallTypeM = totalCostOfCallsForCallTypeM;
    }

    public String getTotalCostOfCallsForCallTypeS() {
        return totalCostOfCallsForCallTypeS;
    }

    public void setTotalCostOfCallsForCallTypeS(String totalCostOfCallsForCallTypeS) {
        this.totalCostOfCallsForCallTypeS = totalCostOfCallsForCallTypeS;
    }

    public String getTotalCostOfCallsForCallTypeP() {
        return totalCostOfCallsForCallTypeP;
    }

    public void setTotalCostOfCallsForCallTypeP(String totalCostOfCallsForCallTypeP) {
        this.totalCostOfCallsForCallTypeP = totalCostOfCallsForCallTypeP;
    }

    public Map<String, Integer> getTotalCostOfCallsForEachAccNum() {
        return totalCostOfCallsForEachAccNum;
    }

    public void setTotalCostOfCallsForEachAccNum(Map<String, Integer> totalCostOfCallsForEachAccNum) {
        this.totalCostOfCallsForEachAccNum = totalCostOfCallsForEachAccNum;
    }
}
