package fr.omathe.web;

import java.util.Enumeration;

public class Http {

	public static void printRequestHeaders(final HttpServletRequest request) {

		final Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			final String headerName = headerNames.nextElement();
			final String headerValue = request.getHeader(headerName);
			System.out.println(headerName + " : " + headerValue);
		}
	}
}
