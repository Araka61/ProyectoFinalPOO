package logico;

public class Usuario {
	
	private String id;
	private String email;
	private String userName;
	private String password;
	private String rol;
	
	public Usuario(String id, String email, String userName, String password, String rol) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getId() {
		return id;
	}
	

}
