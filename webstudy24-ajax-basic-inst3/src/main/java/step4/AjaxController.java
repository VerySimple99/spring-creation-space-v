package step4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/AjaxController.do")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//ajax 요청에 대한 응답이므로 페이지 정보가 아니라 필요한 데이터만 응답한다 
		String nickName=request.getParameter("nickName");
		out.print(nickName+" 별명을 가진 회원의 상세정보~ ajax 통신");
		out.close();
	}
}











