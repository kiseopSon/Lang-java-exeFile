import java.util.Optional;

/*optional 가이드
 * optional 변수에 null 삽입 금지
 * 값이 없을경우 반드시 .orElseX()로 기본값 셋팅
 * 단순한 값을 얻기위한 구조에서 사용금지
 * 생성자, 수정자, 메소드 파라미터를 참조하거나 넘기기 금지
 * 콜렉션인 경우 optional이 아닌 빈 콜렉션을 사용 권장
 * 반드시 반환 타입으로만 사용 
 * 이유 : Optional은 반환 타입으로써 에러가 발생할 수 있는 경우에 결과 없음을 명확히 드러내기 위해 만들어졌음
*/
public class java8Optional{
    public static void main(String[] args) {
        optionalTest option = new optionalTest();
        option.test();
    }
}
//일반적으로 optional은 직렬화를 지원하지 않음
//단, jackson-modules-java8 부터의 라이브러리에 optional은 가능
//optional은 컨테이너 클래스기에 별도의 용량과 접근비용이 추가된다. 그래서 특수한 경우가 아닌경우엔 사용하지 않는것이 좋다.
class optionalTest{
    public void test(){
    }
}

