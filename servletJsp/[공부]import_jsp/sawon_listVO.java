package VO;

public class sawon_listVO {
	//유저정보 저장 : DB에서 클래스로 저장 : VO
	//VO(ValueObject) : DB의 여러가지 정보를 묶어서 저장하는 하나의 클래스
	private int deptno;
	private int sabun;
	private String sajob;
	private String saname;
	
	//생성자 초기화를 해주는 이유는 나중에 VO를 데이터를 직렬화 할경우 서버단의 초기화와 클라이언트단에서의 json타입으로 변환할때 역직렬화시 db와 데이터 구성대로 매핑시키기 위함이다.
	public sawon_listVO(int deptno, int sabun, String sajob, String saname) {
		this.deptno = deptno;
		this.sabun = sabun;
		this.sajob = sajob;
		this.saname = saname;
	}//생성자
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public String getSajob() {
		return sajob;
	}
	public void setSajob(String sajob) {
		this.sajob = sajob;
	}
	public String getSaname() {
		return saname;
	}
	public void setSaname(String saname) {
		this.saname = saname;
	}
}
