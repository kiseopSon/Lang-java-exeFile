import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class GenerateRSA512KeyinBase64 {

    public static void main(String[] args) {
        try {
            // 개인키 생성
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // 개인키를 Base64로 인코딩
            String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            System.out.println("Encoded Private Key:");
            System.out.println(encodedPrivateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}