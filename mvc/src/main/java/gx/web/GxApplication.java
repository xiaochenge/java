package gx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ImportResource(value = {"classpath:/spring-webmvc-context.xml"})
@PropertySource(value = {"classpath:configuration-${spring.profiles.active}.properties"})
public class GxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(GxApplication.class, args);
		/**
		 * 启动netty
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
		 */
	}
}
