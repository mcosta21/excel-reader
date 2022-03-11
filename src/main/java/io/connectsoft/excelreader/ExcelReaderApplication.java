package io.connectsoft.excelreader;

import io.connectsoft.excelreader.models.StudentDTO;
import io.connectsoft.excelreader.services.ExcelReader;
import io.connectsoft.excelreader.services.StudentDTORowMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class ExcelReaderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExcelReaderApplication.class, args);

		var file = new ClassPathResource("data/students.xlsx");

		var excelReader = new ExcelReader<StudentDTO>(file.getInputStream(), new StudentDTORowMapper());
		var items = excelReader.read();
	}

}
