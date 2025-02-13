package com.example.parser;

import com.example.parser.dto.CallDetailsDto;
import com.example.parser.dto.CallDetailsReportDto;
import com.example.parser.service.ParserService;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ParserApplication.class)
class ParserApplicationTests {
    @Autowired
    private  ParserService parser;

    // total cost of calls for call type P
    @Test
    void  totalCostOfCallsForCallTypeP() throws IOException {
        String expect = "90";
        List<CallDetailsDto> callDetailsDtos =  parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        String actual = callDetailsReportDto.getTotalCostOfCallsForCallTypeP().toString();
        Assertions.assertEquals(expect,actual);
    }

    // total cost of calls for call type S
    @Test
    void  totalCostOfCallsForCallTypeS() throws IOException {
        String expect = "92";
        List<CallDetailsDto> callDetailsDtos =   parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        String actual = callDetailsReportDto.getTotalCostOfCallsForCallTypeS().toString();
        Assertions.assertEquals(expect,actual);
    }

    // total cost of calls for call type M
    @Test
    void  totalCostOfCallsForCallTypeM() throws IOException {
        String expect = "34";
        List<CallDetailsDto> callDetailsDtos =   parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        String actual = callDetailsReportDto.getTotalCostOfCallsForCallTypeM().toString();
        Assertions.assertEquals(expect,actual);
    }
    // total cost of all calls for each day

    @Test
    void  totalCostOfCallsEachDay() throws IOException {
        String expect = "216";
        List<CallDetailsDto> callDetailsDtos =   parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        String actual = callDetailsReportDto.getTotalCostOfCallOfADay().toString();
        Assertions.assertEquals(expect,actual);
    }

    // total cost of all calls for each day

    @Test
    void  totalCostOfCallForAccNum46002258() throws IOException {
        String expect = "22";
        List<CallDetailsDto> callDetailsDtos =   parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        Map<String, Integer> totalCostOfCallsForEachAccNum = callDetailsReportDto.getTotalCostOfCallsForEachAccNum();
        String actual = "";
        if(totalCostOfCallsForEachAccNum.containsKey("46002258")){
            actual = totalCostOfCallsForEachAccNum.get("46002258").toString();
        }
        Assertions.assertEquals(expect,actual);
    }
    @Test
    void testValidateFile() throws IOException {
        parser.validate();
    }
    @Test
    void testGenerateReportFile() throws IOException {
        List<CallDetailsDto> callDetailsDtos =   parser.parseFile(parser.readFile());
        CallDetailsReportDto callDetailsReportDto =  parser.generateReport(callDetailsDtos);
        parser.generateReportFile(callDetailsReportDto);
    }
}
