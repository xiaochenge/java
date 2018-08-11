package gx.web;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import gx.common.socket.server.Server;

@SpringBootApplication
public class GxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(GxApplication.class, args);
		/**
		 * 启动netty
		 */
		try {
			new Server(10000,"gx.web", run);
		} catch (SSLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
