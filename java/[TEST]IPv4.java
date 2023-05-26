import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class 각종error테스트 {
	public static void main(String[] args)
    {
		try {
            Inet4Address ipAddress = (Inet4Address) Inet4Address.getLocalHost();
          //  Inet4Address iphost = (Inet4Address) Inet4Address.getByName(ipAddress.toString());
            Inet4Address getLoopbackAddress = (Inet4Address) Inet4Address.getLoopbackAddress();
         //   InetAddress[] getAllByName = Inet4Address.getAllByName(ipAddress.toString());
            System.out.println( Inet4Address.getLocalHost());
            System.out.println( Inet4Address.getLocalHost().getHostName());
            System.out.println( Inet4Address.getLocalHost().getLocalHost());
            System.out.println( Inet4Address.getLocalHost().getLoopbackAddress());
            System.out.println( Inet4Address.getLocalHost().getCanonicalHostName());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(ipAddress.getHostAddress());
          //  System.out.println(iphost.getHostAddress());
            System.out.println(getLoopbackAddress.getHostAddress());
           // System.out.println(getAllByName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
