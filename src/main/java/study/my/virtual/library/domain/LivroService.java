package study.my.virtual.library.domain;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import study.my.virtual.library.s3.S3Service;

@Service
public class LivroService {
	
	@Autowired
	private S3Service s3Service;
	
	public URI uploadLivroPicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
	
}
