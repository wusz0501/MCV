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
我们在浏览器中通过   htt://IP地址:端口号/Day5_MVC/s1.do 来访问这个Servlet

我们的浏览器向服务器发送一个请求,服务器会把这个请求进行包装,产生一个新的HttpServletRequest对象
和一个HttpServletResponse对象.浏览器每来一次请求,都会创建一对请求响应对象.
响应结束之后,服务器和浏览器失去联系

当我们第一次访问一个Servlet的时候,Tomcat服务器会创建这个Servlet的对象,并且调用这个Server
对象的doGet()/doPost()方法.并且把HttpServletRequest,HttpServletResponse分别传递
给doGet()/doPost()
注意:Servlet对象只会被创建一次.
第二次访问这个Sevlet的时候,还是调用以前的个Servlet对象



 */
@WebServlet("/s1.do")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet1() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收客户端表单提交过来的数据的时候,浏览器默认的编码格式是ISO8859-1,而我们使用的编码是UTF-8
		// 所以接收到的中文数据会乱码.我们需要在接收数据之前,让request重新制定编码格式
		// 注意:指定编码格式一定要在从request中获取数据之前进行设置
		request.setCharacterEncoding("utf-8");
		
		// 服务器把浏览器的请求包装成HttpServletRequest对象,并且把这个对象传递给了doGet/doPost
		// 在这个HttpServletRequest中就有浏览器传递过来的表单数据,所有我们要通过HttpServletRequest
		// 来取得表单数据
		// 在取得表单数据的时候,就是通过表单输入框中dname属性来获取input输入框的值的
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");

		System.out.println("在服务器Servlet中接收到表单数据:uname=" + uname + ",password=" + password);
		// 取得用户名和密码之后,执行逻辑判断
		// 判断用户名是否是admin,密码是否是123456
		if ("admin".equals(uname) && "123456".equals(password)) {

			// HttpSession称为一个会话.
			// 什么是会话:客户端和服务器端一个连续不断的交互过程
			// 服务器会为每个客户端浏览器单独的创建一个会话,也就是说每个客户端浏览器都有一个单独的会话
			// 如果客户端浏览器关闭或服务器关闭,或者无操作时间超过30分钟,会话会被中断
			// 我们想会话对象中设置特定的属性来作为通行证
			HttpSession session = request.getSession();
			session.setAttribute("name", uname);

			// 正确,跳转到loginOk.jsp
			// 使用请求分发器
			RequestDispatcher dis = request.getRequestDispatcher("loginOk.jsp");
			dis.forward(request, response);
		} else {
			// 不正确,跳转到index.jsp
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 因为我们不管用户的请求方式是get还是post,我们的处理流程都是一样的
		// 所有我在doPost()去调用doGet()
		doGet(request, response);
	}

}
