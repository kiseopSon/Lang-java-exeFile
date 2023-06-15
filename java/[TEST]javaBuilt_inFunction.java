public class javaBuilt_inFunction {
	public static void main(String[] args) {
		//1
		String str = "abcdefg";
		String str2 = "abc.co.kr";
		char t1 = str.charAt(2);
		System.out.println("1/"+t1);
		//2
		boolean t2 = str.contains("de");
		System.out.println(t2);
		boolean t3 = str.contains("ab");
		System.out.println("2/"+t3);
		//3,4
		boolean t4 = str2.endsWith(".com");
		System.out.println("3/"+t4);
		boolean t5 = str2.endsWith(".co.kr");
		System.out.println(t5);
		boolean t6 = str2.startsWith(".co.kr");
		System.out.println("4/"+t6);
		//5
		int t7 = str2.indexOf("c");
		int t8 = str2.indexOf("c",5);
		System.out.println(t7);//2
		System.out.println(t8);// -1
		System.out.println("5/"+str2.indexOf("!"));
		//6
		String str3 = "123123";
		String str4 = "";
		String str5 = null;
		System.out.println(str3.isEmpty());
		System.out.println("6/"+str4.isEmpty());
//		System.out.println(str5.isEmpty());
		//7
		String t9 = String.join(",","aaa","bbb","ccc");
		System.out.println("7/"+t9);//aaa,bbb,ccc
		//8
		String str6 = "java a java b java c";
		System.out.println(str6.lastIndexOf("java"));
		System.out.println("8/"+str6.lastIndexOf("java",11));
		//9
		int t10 = str6.length();
		System.out.println("9/"+t10+"");
		//10
		String t11 = str6.replace("java", "C++");
		System.out.println("10/"+"t11 :" + t11);
		//11
		 String regExp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})";
		 String str7 = " 010-222-3333, 010.4444.5555 .";
		 String t12 = str7.replaceAll(regExp, "(���)");
	     System.out.println("11/"+t12);
	     String str8 = "010.1111.2222";
	     String str9 = "010!1111!2222";
	     System.out.println(str8.matches(regExp));
	     System.out.println("11/"+str9.matches(regExp));
	     //12
	     String[] t13 = str8.split("[.-]");
	     for(String t : t13) {
	    	 System.out.println(""+ t);
	     }
	     t13 = str6.split(" ");
	     for(String c : t13) {
	    	 System.out.println("12/"+" : " + c);
	     }
	     //13 
	     String t14 = str9.substring(3);
	     String t15 = str9.substring(1,4);
	     System.out.println(t14);
	     System.out.println("13/"+t15);
	     
	     //14
	     //char[] toCharArray()
	     char[] t16 = str9.toCharArray();
	     for(char c : t16) {
	    	 System.out.println("14/"+" : "+ c);
	     }
	     
	     //15
	     
	     String t17 = str6.toUpperCase();
	     System.out.println(t17);
	     System.out.println("15/"+str6.toLowerCase());
	     
	     //16
	     String str11 = "             zzzzzzzzz gggggggggg~";
	     String t18 = str11.trim();
	     System.out.println("16/"+t18);
	     
	     //17
	     String t19 = String.valueOf(1);
	     String t20 = String.valueOf(true);
	     t20 = true + "";
	     System.out.println("17/"+t20);
	}
}
