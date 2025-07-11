package durgaproject.jornalapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class Jornalapp1Application {

	public static void main(String[] args) {
		DotenvLoader.loadEnv();
		SpringApplication.run(Jornalapp1Application.class, args);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String raw = "khua";
		String encode = encoder.encode(raw);
//		String encoded = "$2a$10$UALsV3asYG0Me6FLUrBs.uVPCZWbED0E0OhVkg5x7Rq5yhNDDeYUy";
		System.out.println(encode);

		System.out.println(encoder.matches(raw,encode));
	}
	@Bean
	public PlatformTransactionManager run( MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}



}//platformtranctionmanager
//MongoTransactional
