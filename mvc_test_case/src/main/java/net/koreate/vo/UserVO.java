package net.koreate.vo;

public class UserVO {

	private int uno;
  private String uid;
  private String upw;
  private String uname;
  private int upoint;

public int getUno() {
	return uno;
}

public void setUno(int uno) {
	this.uno = uno;
}

public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getUpw() {
    return upw;
  }

  public void setUpw(String upw) {
    this.upw = upw;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public int getUpoint() {
    return upoint;
  }

  public void setUpoint(int upoint) {
    this.upoint = upoint;
  }

	@Override
	public String toString() {
		return "UserVO [uno=" + uno + ", uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + "]";
	}

  
}
