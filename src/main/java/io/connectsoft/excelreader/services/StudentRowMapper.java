package io.connectsoft.excelreader.services;

import io.connectsoft.excelreader.IRowMapper;
import io.connectsoft.excelreader.models.StudentDTO;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class StudentRowMapper implements IRowMapper<StudentDTO> {

    @Override
    public StudentDTO map(XSSFRow row) {
        StudentDTO student = new StudentDTO();

        student.setName(String.valueOf(row.getCell(0)));
        student.setEmailAddress(String.valueOf(row.getCell(2)));
        student.setPurchasedPackage(String.valueOf(row.getCell(2)));

        return student;
    }
}