package vo;
/*
 VO : Value Object
 1.�������̸� DB�ʵ��� ������ �Ӽ����� ���
 2.�Ӽ�(����)�� ���� getter/setter�����ض�

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