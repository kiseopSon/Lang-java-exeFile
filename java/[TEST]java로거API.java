import java.util.logging.Level;
import java.util.logging.Logger;

public class 로거api {
	private static Logger logger = Logger.getLogger(로거api.class.getName());
	public static void main(String[] args) {
		logger.log(Level.SEVERE,"servere");
		logger.log(Level.INFO,"info");
		logger.log(Level.WARNING,"war");
	}
}
