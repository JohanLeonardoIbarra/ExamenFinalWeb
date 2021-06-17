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
	private EnviarEmail mail;
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
		mail = new EnviarEmail();
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
			case "new":
				//showNewForm(request, response);
				break;
			case "Insert":
				insertarUsuario(request, response);
				break;
			case "delete":
				//eliminarUsuario(request, response);
				break;
			case "edit":
				//showEditForm(request, response);
				break;
			case "update":
				//editarUsuario(request, response);
				break;
			default:
				newRegistro(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Integer state = 0;
		Rol role = rolDao.find(1);
		Usuario u = new Usuario(nombre, email, pass, role, state);
		userDao.insert(u);
		
		response.sendRedirect("ExamenWeb/User");
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
