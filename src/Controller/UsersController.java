package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.RolDao;
import Dao.UsuarioDao;
import Modelo.Rol;
import Modelo.Usuario;
import Utils.EnviarEmail;

/**
 * Servlet implementation class UsersController
 */
@WebServlet("/User/*")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RolDao rolDao;
	private UsuarioDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		rolDao = new RolDao();
		userDao = new UsuarioDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] opt =  request.getRequestURI().split("/");
		String action =  request.getContextPath();
		if (opt.length>3){
		 action = opt[3];
		 System.out.println(action);
		}		
		try {
			switch (action) {
			case "Validar":
				validarUsuario(request, response);
				break;
			case "Insert":
				insertarUsuario(request, response);
				break;
			case "Login":
				logearUsuario(request, response);
				break;
			case "Registro":
				newRegistro(request, response);
				break;
			case "Logear":
				validarLogin(request, response);
				break;
			case "Admin":
				adminUser(request, response);
				break;
			default:
				index(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void adminUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin.jsp");
		dispatcher.forward(request, response);
	}

	private void validarLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		for (Usuario i : userDao.list()){
			if (i.getUsuario().equals(user)&&i.getPass().equals(pass)){
				response.sendRedirect("Admin/");
				return;
			}
		}
		response.sendRedirect("User/Login");
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Index.jsp");
		dispatcher.forward(request, response);
	}

	private void logearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
	}

	private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		Usuario u = userDao.find(Integer.parseInt(id));
		
		u.setState(1);
		
		userDao.update(u);
		response.sendRedirect("../User/Sucess");
	}

	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Integer state = 0;
		Rol role = rolDao.find(1);
		Usuario u = new Usuario(nombre, email, pass, role, state);
		userDao.insert(u);
		for (Usuario i : userDao.list()){
			if (i.getUsuario().equals(nombre)&&i.getPass().equals(pass)){
				Integer id = i.getId();
				EnviarEmail.enviarCorreo(email, id+"");
			}
		}
		
		response.sendRedirect("../User");
	}

	private void newRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Registro.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
