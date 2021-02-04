package com.revolut.bankaccount.serviceImpl;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.servlet.ServletContainer;

import com.revolut.bankaccount.constants.Constants;
import com.revolut.bankaccount.controller.AccountEndpoints;
import com.revolut.bankaccount.controller.TransactionEndpoints;

public class ApplicationStart {
	public static Server server;

	public static void main(String[] args) throws Exception {
		initServer();
		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}

	public static void initServer() {
		if (server == null) {
			synchronized (ApplicationStart.class) {
				if (server == null) {
					QueuedThreadPool threadPool = new QueuedThreadPool(100, 10, 120);
					server = new Server(threadPool);
					ServerConnector connector = new ServerConnector(server);
					connector.setPort(Constants.GENERIC_PORT_NUMBER);
					/*
					 * connector.setHost(address); connector.setPort(port);
					 * connector.setStopTimeout(stopTimeout); connector.setIdleTimeout(idleTimeout);
					 */
					server.setConnectors(new Connector[] { connector });
					ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
					context.setContextPath("/*");
					server.setHandler(context);
					ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
					servletHolder.setInitParameter("jersey.config.server.provider.classnames",
							AccountEndpoints.class.getCanonicalName() + ","
									+ TransactionEndpoints.class.getCanonicalName());
				}
			}
		}
	}
}
