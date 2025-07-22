package step8;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.CarDAO;
import model.CarVO;

/**
 * Servlet implementation class CarJSONArrayServlet
 */
@WebServlet("/CarJSONArrayServlet.do")
public class CarJSONArrayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();
		String maker=request.getParameter("maker");
		ArrayList<CarVO> list=CarDAO.getInstance().getCarList(maker);
		JSONArray jsonArray=new JSONArray(list);
		out.print(jsonArray.toString());
		out.close();
	}
}









