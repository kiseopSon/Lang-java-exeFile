public class 인스턴스초기화블럭 {
	 static {               
		 // 클래스 초기화 블럭
	        System.out.println("static { }");
	    }

	    {                      
	    	// 인스턴스 초기화 블럭
	        System.out.println("{ }");
	    }

	    public 인스턴스초기화블럭() {
	        System.out.println("생성자");
	    }

	    public static void main(String args[]){
	        System.out.println("BlockTest bt = new BlockTest(); ");
	        인스턴스초기화블럭 bt = new 인스턴스초기화블럭();

	        System.out.println("BlockTset bt2 = new BlockTest2();");
	        인스턴스초기화블럭 b2 = new 인스턴스초기화블럭();
	    }
}


