public class 프록시패턴 {
    public static void main(String[] args) {
        // 하나의 메인 도메인 클래스를 생성하고 재활용하는 방식이다.
        // 디자인 패턴중 행위에 해당된다
        // 인터페이스로 구성된걸 개별적으로 설정함으로써 가벼운명령어나 가볍게 작동시킬 수 있다. = 개발자는 인터페이스내에 같은 함수를 사용한다면 같은 작동을 한다고 판단하는 것이다.
        // 현재 테스트용 순서는 생성자가 호출 후 바로 내부함수를 호출 하도록 지시 -> 해당 클래스의 내부 함수를 호출하고 -> 구현체가 호출된다.
        // 동일한 경우는 아니지만, 파악하는데 있어 예를 들자면, 
        // 하나의 클래스에서 함수를 반복적으로 수행해야 하고, 매번 다른 힙주소(=메모리) 를 사용되어야 할때, new 객체().implements(=다른 명령)을 매순간 수행 느낌으로 이해하면 될것 같다.
        // 여기서 장점은 new 객체()의 미리 기본 default로 저장되어있는 함수나 기타 인스턴스변수를 은닉화할 수 있고, 변경되지 않는다는 점이다.
        image test1 = new proxy_image("프록시 테스트1");
        image test2= new proxy_image("프록시 테스트2");

        test1.displayImage();
        test2.displayImage();
    }
}

interface image{
    void displayImage();
}

class real_image implements image{
    private String name;

    public real_image(String name){
        this.name = name;
        loadDisk(this.name);
    }

    public void loadDisk(String name){
        System.out.println("real_image : " + name);
    }

    @Override
    public void displayImage() {
        System.out.println("real_image - displayImage : " + this.name);
    }
}

class proxy_image implements image{
    private real_image real;
    private String name;

    public proxy_image(String name){
        this.name = name;
    }

    @Override
    public void displayImage() {
        if(real == null) real = new real_image(name);
        real.displayImage();
    }

}
