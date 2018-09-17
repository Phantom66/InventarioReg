package com.inventario.con;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorRegUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(request.getParameter("user") + " " + request.getParameter("email") + "\n"
							+ request.getParameter("pass") + " "+ request.getParameter("passConfirm"));
		
	}
}
