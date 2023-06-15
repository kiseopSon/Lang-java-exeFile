// 의존성 추가 - pom.xml에 추가
// <dependency> 
//     <groupId>com.monitorjbl</groupId>
//     <artifactId>xlsx-streamer</artifactId>
//     <version>2.1.0</version> 
// </dependency>

import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.DateUtil; //※이런것도 있음.
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.monitorjbl.xlsx.StreamingReader;
import java.io;
import java.io.FileInputStream;

//+ select쿼리중에 mybatis를 쓴다면, select속성중에 fetchSize 가 있다. 해당 수치 공식은 다음과 같고, y값을 대입하면 된다.
//+ 약 2000만개를 기준, 2000 = 테이블 필드갯수 * x => (x를 구하고) => x* (3/2) = y

public class streamer {
    public static void main(String[] args) {
        File file = new File(path);
        FileInputStream is = new FileInputStream(file);
        // rowCacheSize : 메모리에 보관할 행 수(기본값은 10)
        // bufferSize : InputStream을 파일로 읽을 때 사용할 버퍼 크기(기본값은 1024)
        // open : InputStream 또는 XLSX 파일용 파일(필수)
        Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);

        for (Sheet sheet : workbook) {
            for (Row r : sheet) {
                for (Cell c : r) {
                    switch (c.getCellType()) {
                        case NUMERIC:
                            System.out.println("숫자만 취급");
                            break;

                        default:
                            System.out.println("그외 나머지 타입 취급");
                            break;
                    }
                }
            }
        }
    }
}