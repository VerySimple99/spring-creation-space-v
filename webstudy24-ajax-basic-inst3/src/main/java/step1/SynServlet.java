package step1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SynServlet
 */
@WebServlet("/SynServlet")
public class SynServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//고의로 시간을 지연( 테스트를 위해 ) 
		try {
			Thread.sleep(3000);//3초간 시간 지연 
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		
		out.print("<html>");
		out.print("<body bgcolor=yellow>");
		String id=request.getParameter("userId");
		out.print("고객 아이디:"+id);
		out.print("<br><br>동기적 통신 응답");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}
}











