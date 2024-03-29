package vo;

/*
    VO(Value Object)
    1.값을 저장관리하는 객체(DB경우은 레코드을 말한다)
    2.DTO(Data Transfer Object)라고도 한다(전달객체)
    3.getter/setter무조건 선언해야 된다(값을 입출력할 수 있는 방법 선언)    
    
    4. 3위 일체시켜라(강력권고)
      DB 컬럼명(column) == VO 속성명(property) == 웹:입력parameter명
    5.Overload된 생성자 선언시 기본생성자는 무조건 선언해라
      (역직렬화처리시 기본생성자를 이용해서 객체생성한다)
*/
public class SungVo {

	int    idx;
	String name;
	int    kor;
	int    eng;
	int	   mat;
	
	//client(Java)측에서는 편집할 경우가 없다(DB생성해서 가져오는 데이터)
	//읽기전용데이터 사용할 목적으로 String받는다
	String tot;
	String avg;
	String rank;
	
	//기본생성자
	public SungVo() {
		// TODO Auto-generated constructor stub
	}
	
	//insert
	public SungVo(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	//update
	public SungVo(int idx, String name, int kor, int eng, int mat) {
		super();
		this.idx = idx;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}

	//Overload생성자
	public SungVo(int idx, String name, int kor, int eng, int mat, String tot, String avg, String rank) {
		super();
		this.idx = idx;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = tot;
		this.avg = avg;
		this.rank = rank;
	}
	

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public String getTot() {
		return tot;
	}
	public void setTot(String tot) {
		this.tot = tot;
	}
	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

}
