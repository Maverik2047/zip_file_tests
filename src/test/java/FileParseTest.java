import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipFile;
import static org.assertj.core.api.Assertions.assertThat;


public class FileParseTest {
    ClassLoader classLoader = FileParseTest.class.getClassLoader();

    @Test
    void checkPDFTest() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("tests.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("output.pdf");
        PDF pdf;
        InputStream inputStream = zipFile.getInputStream(entry);
        pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("PDF Example");

    }

    @Test
    void checkXLSTest() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("tests.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("Sample.xlsx");
        XLS xls;
        InputStream inputStream = zipFile.getInputStream(entry);
        xls = new XLS(inputStream);
        assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue())
                .contains("test");
    }
    @Test
    void checkCSVTest() throws Exception{
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("tests.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("file.csv");
        List<String[]> list;
        InputStream inputStream =zipFile.getInputStream(entry);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        list=csvReader.readAll();
        assertThat(list).contains(new String[]
                {"2", "isaac newton", "1643"});

    }
    @Test
    void jsonTest() throws Exception{

    }
}


