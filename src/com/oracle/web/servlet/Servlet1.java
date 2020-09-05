package com.oracle.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
�������������ͨ��   htt://IP��ַ:�˿ں�/Day5_MVC/s1.do ���������Servlet

���ǵ�����������������һ������,������������������а�װ,����һ���µ�HttpServletRequest����
��һ��HttpServletResponse����.�����ÿ��һ������,���ᴴ��һ��������Ӧ����.
��Ӧ����֮��,�������������ʧȥ��ϵ

�����ǵ�һ�η���һ��Servlet��ʱ��,Tomcat�������ᴴ�����Servlet�Ķ���,���ҵ������Server
�����doGet()/doPost()����.���Ұ�HttpServletRequest,HttpServletResponse�ֱ𴫵�
��doGet()/doPost()
ע��:Servlet����ֻ�ᱻ����һ��.
�ڶ��η������Sevlet��ʱ��,���ǵ�����ǰ�ĸ�Servlet����



 */
@WebServlet("/s1.do")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet1() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���տͻ��˱��ύ���������ݵ�ʱ��,�����Ĭ�ϵı����ʽ��ISO8859-1,������ʹ�õı�����UTF-8
		// ���Խ��յ����������ݻ�����.������Ҫ�ڽ�������֮ǰ,��request�����ƶ������ʽ
		// ע��:ָ�������ʽһ��Ҫ�ڴ�request�л�ȡ����֮ǰ��������
		request.setCharacterEncoding("utf-8");
		
		// ��������������������װ��HttpServletRequest����,���Ұ�������󴫵ݸ���doGet/doPost
		// �����HttpServletRequest�о�����������ݹ����ı�����,��������Ҫͨ��HttpServletRequest
		// ��ȡ�ñ�����
		// ��ȡ�ñ����ݵ�ʱ��,����ͨ�����������dname��������ȡinput������ֵ��
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");

		System.out.println("�ڷ�����Servlet�н��յ�������:uname=" + uname + ",password=" + password);
		// ȡ���û���������֮��,ִ���߼��ж�
		// �ж��û����Ƿ���admin,�����Ƿ���123456
		if ("admin".equals(uname) && "123456".equals(password)) {

			// HttpSession��Ϊһ���Ự.
			// ʲô�ǻỰ:�ͻ��˺ͷ�������һ���������ϵĽ�������
			// ��������Ϊÿ���ͻ�������������Ĵ���һ���Ự,Ҳ����˵ÿ���ͻ������������һ�������ĻỰ
			// ����ͻ���������رջ�������ر�,�����޲���ʱ�䳬��30����,�Ự�ᱻ�ж�
			// ������Ự�����������ض�����������Ϊͨ��֤
			HttpSession session = request.getSession();
			session.setAttribute("name", uname);

			// ��ȷ,��ת��loginOk.jsp
			// ʹ������ַ���
			RequestDispatcher dis = request.getRequestDispatcher("loginOk.jsp");
			dis.forward(request, response);
		} else {
			// ����ȷ,��ת��index.jsp
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��Ϊ���ǲ����û�������ʽ��get����post,���ǵĴ������̶���һ����
		// ��������doPost()ȥ����doGet()
		doGet(request, response);
	}

}
