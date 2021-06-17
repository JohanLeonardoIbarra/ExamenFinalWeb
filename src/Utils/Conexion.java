package Utils;

import java.sql.ResultSet;
import java.util.*;
import javax.persistence.*;

public class Conexion <T> {
	
	private Class<T> c;
	private static EntityManager em = null;
	
	public Conexion() {
		em = Conexion.getEm();
	}
	
	public Conexion(Class<T> c) {
		em = Conexion.getEm();
		this.c = c;
	}
	
	public void setC(Class<T> c){
		this.c = c;
	}
	
	public static EntityManager getEm(){
		if ( em == null ) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamenWeb");
            em = emf.createEntityManager();
        }
		return em;
	}
	
	public <E> T find(E id){
		T object = (T) em.find(c, id);
		return object;
	}
	
	public List<T> list(){
		return em.createQuery("from " + c.getSimpleName().toLowerCase(), c).getResultList();
	}
	
	public void insert(T obj){
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void update(T obj){
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void delete(T obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
}

