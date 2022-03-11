package io.connectsoft.excelreader.services;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface IRowMapper<T> {
    T map(XSSFRow row);
}
