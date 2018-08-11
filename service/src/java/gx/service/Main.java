package gx.service;

import gx.common.socket.client.Client;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {



	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext run = new ClassPathXmlApplicationContext("spring-service-context.xml");
		try {
			new Client("127.0.0.1",10000,run);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

			}
