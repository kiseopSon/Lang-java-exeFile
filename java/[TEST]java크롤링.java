import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * test
 */
public class java크롤링 {

    /**
     * @param args
     */
    public static void main(String[] args){
        String url = "https://www.naver.com/";
       Document doc;
    try {
        doc = Jsoup.connect(url).get();
        System.out.println(doc);
        // String filePath = "C:/Users/miso/Desktop/test_vsCode/[양식]java크롤링.txt";
        
        // FileWriter fw = new FileWriter(filePath, true); 
        // PrintWriter out = new PrintWriter(fw, true);
        
        // Elements elements = doc.select("div");
        // for (Element element : elements) {
        //     out.println(element);
        // }
;
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}