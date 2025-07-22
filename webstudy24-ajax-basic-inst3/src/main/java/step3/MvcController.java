package step3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MvcController
 */
@WebServlet("/MvcController.do")
public class MvcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// DAO 연동했다고 가정 
    	String nickName=request.getParameter("nickName");
    	request.setAttribute("info", nickName+" 별명을 가진 회원의 상세정보");
    	request.getRequestDispatcher("step5-syn-result.jsp").forward(request, response);
	}
}











