package com.example.parser.service.impl;

import com.example.parser.dto.CallDetailsDto;
import com.example.parser.dto.CallDetailsReportDto;
import com.example.parser.service.ParserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ParserServiceImpl implements ParserService {

    @Override
    public void validate() throws IOException {
        Resource[] resources =  this.readFile();
        for (Resource resource : resources) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    validLine(line);
                    validateLength(line);
                }
            }
        }

//        validLine(callDetailsDtos);
//        validateLength(callDetailsDtos);
    }
    private void validLine(String line){
        StringTokenizer sb = new StringTokenizer(line, " ");
        if(sb.countTokens()!=4){
            throw new RuntimeException("Invalid line, not enough column: "+ line);
        }
    }
    private void validateLength(String line) {
        StringTokenizer sb = new StringTokenizer(line, " ");
        if(sb.nextToken().length() > 10){
            throw new RuntimeException("Invalid line, acc num length not valid: " + line);
        }
        if(sb.nextToken().length() > 15){
            throw new RuntimeException("Invalid line, A_NUM length not valid: " + line);
        }
        if(sb.nextToken().length() > 15){
            throw new RuntimeException("Invalid line, B_NUM length not valid: " + line);
        }

        if(sb.nextToken().length() <= 29){
            throw new RuntimeException("Invalid line,  STT_TIME length not valid: " + line);
        }
    }


    @Override
    public Resource[] readFile() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:" + "inputdata" + "/*.txt");
        return resources;
    }

    @Override
    public  List<CallDetailsDto> parseFile(Resource[] resources) throws IOException {
        List<CallDetailsDto> callDetailsDtoList = new ArrayList<>();
        for (Resource resource : resources) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    StringTokenizer sb = new StringTokenizer(line);
                    CallDetailsDto callDetailsDto = new CallDetailsDto();
                    callDetailsDto.setAccNum(sb.nextToken().toString());
                    callDetailsDto.setaNum(sb.nextToken().toString());
                    callDetailsDto.setbNum(sb.nextToken().toString());
                    String typeCost = sb.nextToken().toString();
                    if (typeCost.contains("P")) {
                        callDetailsDto.setCallType("P");
                    }
                    if (typeCost.contains("S")) {
                        callDetailsDto.setCallType("S");
                    }
                    if (typeCost.contains("M")) {
                        callDetailsDto.setCallType("M");
                    }
                    callDetailsDto.setCallCost(typeCost.toString().substring(29));
                    callDetailsDtoList.add(callDetailsDto);
                }
            }
        }
        return callDetailsDtoList;
    }


    @Override
    public  CallDetailsReportDto generateReport(List<CallDetailsDto> callDetailsDtos) {
        List<CallDetailsReportDto> callDetailsReportDtos = new ArrayList<>();
        CallDetailsReportDto callDetailsReportDto = new CallDetailsReportDto();

        // Total cost of calls for each account number in the file
        Map<String, Integer> totalCostOfCallsForEachAccNum = callDetailsDtos.stream()
                .collect(Collectors.groupingBy(
                        CallDetailsDto::getAccNum,                      // Group by accNum
                        Collectors.summingInt(item -> Integer.valueOf(item.getCallCost())))); // Sum the quantities
        // Total cost of calls for each call type in the file
        Map<String, Integer> totalCostOfCallsForEachCallType = callDetailsDtos.stream()
                .collect(Collectors.groupingBy(
                        CallDetailsDto::getCallType,                      // Group by accNum
                        Collectors.summingInt(item -> Integer.valueOf(item.getCallCost())))); // Sum the quantities

        callDetailsReportDto.setTotalCostOfCallsForEachAccNum(totalCostOfCallsForEachAccNum);

        if(totalCostOfCallsForEachCallType.containsKey("P")){
            callDetailsReportDto.setTotalCostOfCallsForCallTypeP(totalCostOfCallsForEachCallType.get("P").toString());
        }
        if(totalCostOfCallsForEachCallType.containsKey("S")){
            callDetailsReportDto.setTotalCostOfCallsForCallTypeS(totalCostOfCallsForEachCallType.get("S").toString());
        }
        if(totalCostOfCallsForEachCallType.containsKey("M")){
            callDetailsReportDto.setTotalCostOfCallsForCallTypeM(totalCostOfCallsForEachCallType.get("M").toString());
        }
        callDetailsReportDto.setTotalCostOfCallOfADay(String.valueOf(callDetailsDtos.stream().mapToInt(i->Integer.valueOf(i.getCallCost())).sum()));
        return callDetailsReportDto;
    }

    @Override
    public void generateReportFile(CallDetailsReportDto callDetailsReportDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/report" + UUID.randomUUID().toString() +  ".txt"), callDetailsReportDto);
    }

}
