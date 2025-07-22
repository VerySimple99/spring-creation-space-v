package step5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//요청 데이터(request)에 대한 한글처리 
		//응답데이터에 대한 한글처리 
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String message=request.getParameter("message");
		System.out.println(email+" "+message+": 고객 이메일과 메세지 정보 등록완료");
		out.print(email+" "+message+": 고객 이메일과 메세지 정보 등록완료");
		out.close();
	}
}


















