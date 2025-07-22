package step2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ASynServlet
 */
@WebServlet("/ASynServlet")
public class ASynServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 고의로 시간을 지연( 테스트를 위해 )
		try {
			Thread.sleep(3000);// 3초간 시간 지연
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		count++;
		System.out.println("client에서 전송받은 id:" + id+" "+count);
		out.print(id + "님 ajax 응답^^ count:"+count);// ajax 통신이므로 페이지가 아니라 필요한 데이터만 응답한다
		out.close();
	}
}



