package com.swiss.re.employee.portal.services;

import com.swiss.re.employee.portal.EmpUtil;
import com.swiss.re.employee.portal.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="excel")
public class ExcelService {
    @Autowired
    private ResourceLoader resourceLoader;

    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static Logger log = LogManager.getLogger(ExcelService.class);

    public List<Employee> getEmployees() {
        log.info("employee service- {}", filePath);

        List<Employee> empList = new ArrayList<>();

        Resource resource = resourceLoader.getResource(filePath);

        try(InputStream in = resource.getInputStream()){
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            boolean isHeader = true;
            for(Row row: sheet){
                if (isHeader) {
                    isHeader = false;
                    continue; // skip header
                }
                Employee e = EmpUtil.sheetRowToPojo(row);
                empList.add(e);
            }

        }catch(IOException e){
            log.error(e);
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return empList;
    }
}
