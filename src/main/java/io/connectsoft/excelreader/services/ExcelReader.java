package io.connectsoft.excelreader.services;

import io.connectsoft.excelreader.IRowMapper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reflections.Reflections;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

public class ExcelReader<T> {

    private InputStream file;
    private IRowMapper<T> rowMapper;
    private boolean skipFirstLine = true;

    public ExcelReader(InputStream inputStream, IRowMapper<T> rowMapper) {
        this.file = inputStream;
        this.rowMapper = rowMapper;
    }

    public ExcelReader(InputStream inputStream, IRowMapper<T> rowMapper, boolean skipFirstLine) {
        this.file = inputStream;
        this.rowMapper = rowMapper;
        this.skipFirstLine = skipFirstLine;
    }

    public List<T> read() throws Exception {
        if(file == null) {
            throw new Exception("File not found");
        }

        var items = new ArrayList<T>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            var firstLine = this.skipFirstLine ? 1 : 0;
            for(int i = firstLine; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);
                T item = rowMapper.map(row);
                items.add(item);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }


}