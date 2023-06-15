package common;

public class Common {
	//게시판별 관리를 편하게 하기 위한 클래스
	public static class Board{
		//한페이지당 보여줄 게시물 수
		public final static int BLOCKLIST = 4;
		
		//한 화면에 보여지는 페이지 메뉴 수
		//< 1 2 3 4 5 >
		public final static int BLOCKPAGE = 5;
	}
	
	//공지사항 게시판
	public static class Notice{
		public final static int BLOCKLIST = 5;
		public final static int BLOCKPAGE = 3;
	}
}