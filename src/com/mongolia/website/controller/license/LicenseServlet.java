package com.mongolia.website.controller.license;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class LicenseServlet
 */
public class LicenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LicenseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		InputStream stram = this.getClass().getResourceAsStream(
				"/resources/config.properties");
		Properties p = new Properties();
		try {
			p.load(stram);
			// BASE64Encoder BASE64Encoder=new BASE64Encoder();
			// String encodestr=BASE64Encoder.encode("2014-12-31".getBytes());
			// System.out.println(encodestr);
			String licensekey = p.getProperty("licensekey");
			if (licensekey == null) {
				Log.info("没有认证文件");
				System.exit(0);
			}
			BASE64Decoder decoder = new BASE64Decoder();
			byte decode[] = decoder.decodeBuffer(licensekey);
			String licencedate = new String(decode);
			java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			Date data1 = dateformat.parse(licencedate);
			if (System.currentTimeMillis() > data1.getTime()) {
				Log.info("认证已到期！");
				System.exit(0);
			}
			config.getServletContext().setAttribute("licensekey", licensekey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (ParseException pex) {
			pex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
