package study.my.virtual.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import study.my.virtual.library.s3.S3Service;

@SpringBootApplication
<<<<<<< HEAD
public class Application {
	
=======
public class Application implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;
>>>>>>> 617840efa4a06f33aebe1a306c4e427f259dc077

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

<<<<<<< HEAD
=======
	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Users\\Nova\\Documents\\Isadora_Documentos\\zoeykravitz.jpg");
	}
>>>>>>> 617840efa4a06f33aebe1a306c4e427f259dc077

}
