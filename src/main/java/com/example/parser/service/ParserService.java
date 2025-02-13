package com.example.parser.service;

import com.example.parser.dto.CallDetailsDto;
import com.example.parser.dto.CallDetailsReportDto;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ParserService {
    public void validate() throws IOException;
    public Resource[] readFile() throws IOException;
    public List<CallDetailsDto> parseFile(Resource[] resources) throws IOException;
    public CallDetailsReportDto generateReport(List<CallDetailsDto> callDetailsDtos);
    public void generateReportFile(CallDetailsReportDto callDetailsReportDto) throws IOException;
}
