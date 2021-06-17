package Dao;

import Modelo.Rol;
import Utils.Conexion;

public class RolDao extends Conexion<Rol>{
	
	public RolDao(){
		super(Rol.class);
	}
	
}
