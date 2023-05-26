import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class timeTransfer {
	public static void main(String[] args) {
		String searchDate = "2021-05-04";
		LocalDate localDateNow = LocalDate.now();
		String[] date = searchDate.split("-");
		
		LocalDate localDate = null;
		localDate = LocalDate.of(dateParse(date[0]), dateParse(date[1]), dateParse(date[2]));
		String date1 = ""+LocalDate.now().minusDays(1);
		String date2 = ""+LocalDate.now().minusDays(2);
		System.out.println(localDateNow);
		System.out.println(localDate);
		System.out.println(date1);
		System.out.println(date2);

		LocalDate date3 = LocalDate.now();
        String dateNow = date3.getYear() + "/"+ (date3.getMonthValue() < 10 ? "0"+date3.getMonthValue() : date3.getMonthValue());
        System.out.println("dateNow : " + dateNow);
	}
	public static int dateParse(String date) {
		int result = Integer.parseInt(date);
		return result;
	}
}

