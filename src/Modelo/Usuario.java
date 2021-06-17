package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "usuario")
@Table (name = "usuario")
public class Usuario{
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "usuario", nullable = false, length = 20)
	private String usuario;
	@Column (name = "email", nullable = false, length = 100)
	private String email;
	@Column (name = "pass", nullable = false, length = 50)
	private String pass;
	@ManyToOne
	@JoinColumn (name = "rol")
	private Rol role;
	@Column (name = "state", nullable = false)
	private Integer state;
	public Usuario(String usuario, String email, String pass, Rol role, Integer state) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.state = state;
	}
	
	
}
