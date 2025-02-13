package com.example.parser;

import com.example.parser.dto.CallDetailsDto;
import com.example.parser.dto.CallDetailsReportDto;
import com.example.parser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParserApplication {


    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
    }

}
