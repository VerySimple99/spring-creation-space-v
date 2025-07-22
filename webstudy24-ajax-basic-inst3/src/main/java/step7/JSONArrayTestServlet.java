package step7;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class JSONArrayTestServlet
 */
@WebServlet("/JSONArrayTestServlet.do")
public class JSONArrayTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();
		JSONArray jsonArray=new JSONArray();
		jsonArray.put("카스");
		jsonArray.put("테라");
		jsonArray.put("켈리");
		// ajax 통신이므로 페이지가 아니라 필요한 데이터만 응답된다 
		out.print(jsonArray.toString());
		out.close();
	}
}







