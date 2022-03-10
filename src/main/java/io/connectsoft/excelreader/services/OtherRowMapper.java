package io.connectsoft.excelreader.services;

import io.connectsoft.excelreader.IRowMapper;
import io.connectsoft.excelreader.models.OtherDTO;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class OtherRowMapper implements IRowMapper<OtherDTO> {

    @Override
    public OtherDTO map(XSSFRow row) {
        return null;
    }
}