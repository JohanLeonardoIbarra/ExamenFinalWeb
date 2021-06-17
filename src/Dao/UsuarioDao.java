package Dao;

import Modelo.Usuario;
import Utils.Conexion;

public class UsuarioDao extends Conexion<Usuario>{
	
	public UsuarioDao(){
		super(Usuario.class);
	}
	
}
