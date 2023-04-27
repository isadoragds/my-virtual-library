package study.my.virtual.library.s3;

<<<<<<< HEAD
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
=======
import java.io.File;
>>>>>>> 617840efa4a06f33aebe1a306c4e427f259dc077

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

=======

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	
>>>>>>> 617840efa4a06f33aebe1a306c4e427f259dc077
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3cliente;
<<<<<<< HEAD

	@Value("${s3.bucket}")
	private String bucketName;

	// método responsavel por fazer upload do arquivo que passer pelo caminho para o
	// s3
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();

			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3cliente.putObject(bucketName, fileName, is, meta);
			LOG.info("Finalizado upload");

			return s3cliente.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}

=======
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	//método responsavel por fazer upload do arquivo que passer pelo caminho para o s3
	public void uploadFile(String localFilePath) {
		
		try {
		File file = new File(localFilePath);
		LOG.info("Iniciando upload");
		s3cliente.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
		LOG.info("Finalizado upload");
		}
		catch(AmazonServiceException e){
			LOG.info("AmazonServiceException: " + e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
		}
		catch(AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}
	}
	
>>>>>>> 617840efa4a06f33aebe1a306c4e427f259dc077
}
