package io.connectsoft.excelreader.services;

import io.connectsoft.excelreader.models.StudentDTO;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class StudentDTORowMapper implements IRowMapper<StudentDTO> {

    @Override
    public StudentDTO map(XSSFRow row) {
        StudentDTO student = new StudentDTO();

        student.setName(String.valueOf(row.getCell(0)));
        student.setEmail(String.valueOf(row.getCell(1)));
        student.setLevel(String.valueOf(row.getCell(2)));

        return student;
    }
}