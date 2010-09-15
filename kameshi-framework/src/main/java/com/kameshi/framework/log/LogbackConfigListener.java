package com.kameshi.framework.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Bootstrap listener for custom Logback initialization in a web environment.
 * Delegates to LogbackWebConfigurer.
 * <p/>
 * <b>WARNING: Assumes an expanded WAR file</b>, both for loading the
 * configuration file and for writing the log files. If you want to keep your
 * WAR unexpanded or don't need application-specific log files within the WAR
 * directory, don't use Logback setup within the application (thus, don't use
 * Log4jConfigListener or LogbackConfigServlet). Instead, use a global, VM-wide
 * Log4J setup (for example, in JBoss) or JDK 1.4's
 * <code>java.util.logging</code> (which is global too).
 * <p/>
 * <p>
 * This listener should be registered before ContextLoaderListener in web.xml,
 * when using custom Logback initialization.
 * <p/>
 * <p>
 * For Servlet 2.2 containers and Servlet 2.3 ones that do not initalize
 * listeners before servlets, use Log4jConfigServlet. See the
 * ContextLoaderServlet javadoc for details.
 * 
 * @since 27-set-2007 13.57.04
 * @see LogbackWebConfigurer
 * @see LogbackConfigListener
 * @see org.springframework.web.context.ContextLoaderListener
 * @see org.springframework.web.context.ContextLoaderServlet
 * @see org.springframework.web.util.WebAppRootListener
 */
public class LogbackConfigListener implements ServletContextListener {
	/**
	 * {@inheritDoc}
	 * 
	 * @param event
	 *            {@inheritDoc}
	 */
	public void contextInitialized(ServletContextEvent event) {
		LogbackWebConfigurer.initLogging(event.getServletContext());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param event
	 *            {@inheritDoc}
	 */
	public void contextDestroyed(ServletContextEvent event) {
		LogbackWebConfigurer.shutdownLogging(event.getServletContext());
	}
}
