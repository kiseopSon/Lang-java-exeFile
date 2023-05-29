import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDate윤년계산 {
    public static void main(String[] args) {
        int year = 2023;
        int month = 1;
        int day = 1;
        int count = 0;
        
        LocalDate date = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        while (date.getYear() == year) {
            if (count % 7 == 0 && count != 0) {
                System.out.print("\n");
            }
            System.out.print(date.format(formatter) + " ");
            date = date.plusDays(1);
            count++;
            
            // 윤년 계산
            if (date.getMonthValue() == 2 && date.getDayOfMonth() == 29) {
                if (!date.isLeapYear()) {
                    date = date.plusDays(1);
                }
            }
        }
    }
}