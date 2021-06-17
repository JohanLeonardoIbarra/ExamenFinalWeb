package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity (name = "rol")
@Table (name = "rol")
public class Rol {
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "description", nullable = false, length = 100)
	private String description;
}
