package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "role")
@Table (name = "role")
public class Rol {
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "descripcion", nullable = false, length = 100)
	private String descripcion;
}
