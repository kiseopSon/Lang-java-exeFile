import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//<dependency>
//	<groupId>io.jsonwebtoken</groupId>
//	<artifactId>jjwt-api</artifactId>
//	<version>0.11.2</version>
//</dependency>
//<dependency>
//	<groupId>io.jsonwebtoken</groupId>
//	<artifactId>jjwt-impl</artifactId>
//	<version>0.11.2</version>
//	<scope>runtime</scope>
//</dependency>
//<dependency>
//	<groupId>io.jsonwebtoken</groupId>
//	<artifactId>jjwt-jackson</artifactId>
//	<version>0.11.2</version>
//	<scope>runtime</scope>
//</dependency>

//jar파일로 단위테스트를 확인하려했으나, 클래스를 찾지못하는 이슈로 maven프로젝트에 dependency를 추가시켜 진행후, 검증까지 마침.
public class JwtToken {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 24시간

    public static String generateToken(String subject) {
    	System.out.println("시크릿키 getEncoded : [B@1453e712 - 계속바뀜" );
    	System.out.println("시크릿키 toString : javax.crypto.spec.SecretKeySpec@fa77faa5 - 값이 안바뀜" );
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")//jwt권장서에서 type보단 typ을 제시한다, 다른 시스템에서 읽을 수 없을 수도 있다고 한다.
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
        	//방금 만들어진 jwt값을 비교하는데 아니면 false
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSubjectFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    //결과 확인 메서드
    
//    String subject = "테스트주제";
//
//    String token = generateToken(subject);
//    System.out.println("생성 Token: " + token);
//
//    boolean isValid = validateToken(token);
//    System.out.println("Token 상태 확인 (true/false): " + isValid);
//
//    String extractedSubject = getSubjectFromToken(token);
//    System.out.println("추출한 주제명 : " + extractedSubject);

}
