import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class StudentServlet
 */

public class Serve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final List<Student> students = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public Serve() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	int id = 1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String course = request.getParameter("course");
		Student student = new Student(id++, name, email, course);
		students.add(student);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();

		if (path == null) {
			response.setContentType("application/json");
			response.getWriter().println(students.toString());
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			for (Student student : students) {
				if (student.getId() == id) {
					response.setContentType("application/json");
					response.getWriter().println(student.toString());
				}
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		for (Student student : students) {
			if (student.getId() == id) {
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String course = request.getParameter("course");
				student.setName(name);
				student.setEmail(email);
				student.setCourse(course);
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		for (Student student : students) {
			if (student.getId() == id) {
				students.remove(id - 1);
			}
		}
	}
}
