package com.swiss.re.employee.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;

public class EmpUtil {

    public static Employee sheetRowToPojo(Row row){
        Employee e = new Employee();
        e.setId((int) row.getCell(0).getNumericCellValue());
        e.setName(row.getCell(1).getStringCellValue());
        e.setCity(row.getCell(2).getStringCellValue());
        e.setState(row.getCell(3).getStringCellValue());
        e.setCategory(EmpCategory.valueOf(row.getCell(4).getStringCellValue()));
        if(row.getCell(5) == null || row.getCell(5).getCellType().equals(CellType.BLANK)){
            e.setManagerId(0);
        }else{
            e.setManagerId((int) row.getCell(5).getNumericCellValue());
        }
        e.setSalary(row.getCell(6).getNumericCellValue());
        e.setDoj(LocalDate.parse(row.getCell(7).getStringCellValue()));

        return e;
    }
}
