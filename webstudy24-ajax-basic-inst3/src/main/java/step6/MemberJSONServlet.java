package step6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.MemberVO;

/**
 * Servlet implementation class MemberJSONServlet
 */
@WebServlet("/MemberJSONServlet.do")
public class MemberJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=utf-8");		
		PrintWriter out=response.getWriter();
		String memberId=request.getParameter("memberId");
		//DAO 연동한다고 가정 
		MemberVO vo=null;
		if(memberId.equals("java")) {
			vo=new MemberVO(memberId,"아이유","판교");
		}else if(memberId.equals("spring")) {
			vo=new MemberVO(memberId,"손흥민","토트넘");
		}else if(memberId.equals("ajax")) {
			vo=new MemberVO(memberId,"김민재","나폴리");
		}
		// ajax 응답 : JSONObject 형식으로 응답한다 
		JSONObject json=new JSONObject(vo);
		out.print(json.toString());
		out.close();
	}
}










