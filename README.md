# Excel Reader

This project is a simple Excel file reader. Using the `ExcelReader.java`, and passing a xlsx file and a mapper class that implements the `IRowMapper`, you get a result list to yout own custom class.

#### ExcelReader class

The ExcelReader class uses a Generic class to return a typed list, uses only its own custom class, and pass the file and the mapper. 
The implementation is based in **[Apache POI](https://poi.apache.org/)** resources to read the file, and it gets the rows and columns. 

````java 
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
````

#### IRowMapper interface
````java
public interface IRowMapper<T> {
    T map(XSSFRow row);
}
````

## Example

Import a list of students with name, email and level;

| NAME      | EMAIL | LEVEL |
| ----------- | ----------- | ----------- |
| Marcio      | marcioc424@gmail.com       | master       |
| Teste   | teste@gmail.com        | smarter       |
| Teste 2  | teste2@gmail.com        | intermediate       |

> java/resources/data/students.xlsx

#### StudentDTO
````java
@Data
public class StudentDTO {
    private String email;
    private String name;
    private String level;
}
````

#### StudentDTORowMapper
````java
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
````

### Using the ExcelReader to StudentDTO

````java
@SpringBootApplication
public class ExcelReaderApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExcelReaderApplication.class, args);

		var file = new ClassPathResource("data/students.xlsx");

		var excelReader = new ExcelReader<StudentDTO>(file.getInputStream(), new StudentDTORowMapper());
		var items = excelReader.read();
	}
}
````

### Result

- StudentDTO(email=marcioc424@gmail.com, name=Marcio, level=master)
- StudentDTO(email=teste@gmail.com, name=Teste, level=starter)
- StudentDTO(email=teste2@gmail.com, name=Teste 2, level=intermediate)

<br/>

> Developed by [Marcio Costa](https://www.linkedin.com/in/mcosta21/).