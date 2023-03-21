package vo;
/*
 VO : Value Object
 1.가급적이면 DB필드명과 동일한 속성명을 사용
 2.속성(변수)에 대한 getter/setter생성해라

 */
public class GogekVo {
	int godam,gobun;
	String gojumin,goaddr,goname;
	
	
	public int getGodam() {
		return godam;
	}
	public void setGodam(int godam) {
		this.godam = godam;
	}
	public int getGobun() {
		return gobun;
	}
	public void setGobun(int gobun) {
		this.gobun = gobun;
	}
	public String getGojumin() {
		return gojumin;
	}
	public void setGojumin(String gojumin) {
		this.gojumin = gojumin;
	}
	public String getGoaddr() {
		return goaddr;
	}
	public void setGoaddr(String goaddr) {
		this.goaddr = goaddr;
	}
	public String getGoname() {
		return goname;
	}
	public void setGoname(String goname) {
		this.goname = goname;
	}
	


}