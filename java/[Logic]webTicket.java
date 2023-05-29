import javax.net.ssl.*; 
import java.io.*;
import java.net.URL; 
import java.security.*; 
import java.security.cert.CertificateException; 
 
public class webTicket 
{ 
	public static void main(String[] args) 
	{ 
		String xrfkey = "16자"; //CSRF이슈 대처키
		String host = "win-nluav1tb3qr"; //서버 호스트명
		String vproxy = "";
		String port = "";
		try 
		{
			String certFolder = "D:/shinhanQlik/cert/"; //인증서 절대경로
			String proxyCert = certFolder + "client.jks"; //클라이언트인증서 상대경로
			String proxyCertPass="123456"; //인증서 비밀번호
			String rootCert = certFolder + "root.jks"; //루트인증서 상대경로
			String rootCertPass = "123456"; //루트인증서 절대경로
			
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(new File(proxyCert)), proxyCertPass.toCharArray()); 
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); 
			kmf.init(ks, proxyCertPass.toCharArray()); 
			SSLContext context = SSLContext.getInstance("SSL"); 
			KeyStore ksTrust = KeyStore.getInstance("JKS"); 
			ksTrust.load(new FileInputStream(rootCert), rootCertPass.toCharArray()); 
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
			tmf.init(ksTrust); 
			context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null); 
			SSLSocketFactory sslSocketFactory = context.getSocketFactory();

			BufferedReader in = null;
			URL url = new URL("https://" + host + ":"+port+"/" + vproxy + "/ticket?xrfkey=" + xrfkey); 
			HttpsURLConnection connection = (HttpsURLConnection ) url.openConnection(); 
			connection.setSSLSocketFactory(sslSocketFactory); 
			connection.setRequestProperty("xrfkey", xrfkey); connection.setDoOutput(true); 
			connection.setDoInput(true); 
			connection.setRequestProperty("Content-Type","application/json"); 
			connection.setRequestProperty("Accept", "application/json"); 
			connection.setRequestMethod("POST");
			/************** BEGIN JSON Message to Qlik Sense Proxy API **************/

			String body = "{";
			//뭔가 내용을 집어넣고 싶을땐 여기에
			body+= "}";
			
			OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream()); 
			wr.write(body); 
			wr.flush();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			StringBuilder builder = new StringBuilder(); 
			String inputLine; 
			while ((inputLine = in.readLine()) != null) 
			{ 
				builder.append(inputLine); 
			} 
			in.close(); 
			String data = builder.toString(); 
			System.out.println("response Result: " + data);
		} 
		catch (KeyStoreException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (CertificateException e) { e.printStackTrace(); } 
		catch (NoSuchAlgorithmException e) { e.printStackTrace(); } 
		catch (UnrecoverableKeyException e) { e.printStackTrace(); } 
		catch (KeyManagementException e) { e.printStackTrace(); } 
	} 
}