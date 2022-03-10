package io.connectsoft.excelreader;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface IRowMapper<T> {
    T map(XSSFRow row);
}
