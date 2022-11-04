package hoon.pepper.common.client.awss3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import hoon.pepper.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class AwsS3Service {

    @Value("${spring.aws.bucket}")
    private String bucket;

    private AmazonS3 s3Client;

    private static final Regions clientRegion = Regions.AP_NORTHEAST_2;

    public AwsS3Service(
        @Value("${spring.aws.access-key}") String accessKey,
        @Value("${spring.aws.secret-key}") String secretKey) {
        createS3Client(accessKey, secretKey);
    }

    /**
     * AWS S3 객체 생성
     */
    private void createS3Client(String accessKey, String secretKey) {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(clientRegion);
        this.s3Client = amazonS3ClientBuilder.build();
    }

    /**
     * File 인풋 업로드
     * @param file 업로드할 File
     * @param key 구분 key
     * @return 업로드 성공 후 키 리턴
     */
    public String upload(File file, String key) {

        try {

            PutObjectRequest request = new PutObjectRequest(this.bucket, key, file);
            this.s3Client.putObject(request);

        } catch (AmazonServiceException e) {

            throw new AmazonServiceException(e.getErrorMessage());

        } catch (SdkClientException e) {

            throw new SdkClientException(e.getMessage());

        }

        return key;
    }

    /**
     * MultiPart 인풋 업로드
     * @param file 업로드할 File
     * @return 업로드 성공 후 키 리턴
     */
    public PutObjectResult uploadMultiPart(MultipartFile file) {

        //키 생성에 대한 논의 필요
        String key = UUID.randomUUID().toString();
        PutObjectResult result;

        try {

            ObjectMetadata metadata = new ObjectMetadata();
            // content length 를 추가해야 content type 이 정상적으로 작동
            // https://stackoverflow.com/questions/30706218/why-does-file-uploaded-to-s3-have-content-type-application-octet-stream-unless-i
            metadata.setContentLength(file.getSize());
            // 템플릿 처리 후에는 content type 이 null 이 되기 때문에 확장자를 찾아서 html 이면 text/html 로 content type 지정
            String contentType = !ObjectUtils.isEmpty(file.getContentType())
                ? file.getContentType()
                : (file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1)).equals("html") ? "text/html" : null;
            metadata.setContentType(contentType);
            PutObjectRequest request = new PutObjectRequest(bucket, key, file.getInputStream(), metadata).withMetadata(metadata);
            result = s3Client.putObject(request);

        } catch (AmazonServiceException e) {

            throw new AmazonServiceException(e.getErrorMessage());

        } catch (Exception e) {

            throw new PlatformException(e.getMessage());
        }

        result.setVersionId(key);
        return result;
    }

    /**
     *
     * @param key 구분 key
     * @return AWS S3 객체 데이터 리턴
     */
    public S3Object getFile(String key) {
        return s3Client.getObject(bucket, key);
    }

    /**
     * 파일 다운로드 링크 생성
     * @param key 구분 key
     * @return 생성한 링크 리턴
     */
    public String getDownloadUrl(String key) {

        URL url;
        Date expiration = new Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        try {

            // 만료시간이 있는 다운로드 url 생성
            GeneratePresignedUrlRequest request =
                    new GeneratePresignedUrlRequest(this.bucket, key)
                            .withExpiration(expiration)
                            .withMethod(HttpMethod.GET);
            url = s3Client.generatePresignedUrl(request);

        } catch (AmazonServiceException e) {

            throw new AmazonServiceException(e.getErrorMessage());

        } catch (SdkClientException e) {

            throw new SdkClientException(e.getMessage());
        }

        return url.toString();
    }

    /**
     * 업로드 한 파일 복사
     * @param orgKey 원 파일의 키
     * @param copyKey 복사한 파일의 키
     */
    public String copy(String orgKey, String copyKey) {

        try {

            //복사 객체 생성 및 복사
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(this.bucket, orgKey, this.bucket, copyKey);
            this.s3Client.copyObject(copyObjRequest);

        } catch (AmazonServiceException e) {

            throw new AmazonServiceException(e.getErrorMessage());

        } catch (SdkClientException e) {

            throw new SdkClientException(e.getMessage());
        }

        return copyKey;
    }

    /**
     * 파일 삭제
     * @param key 구분 key
     * @return 삭제 후 성공시 키 리턴
     */
    public String delete(String key) {
        try {
            //Delete 객체 생성
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, key);
            this.s3Client.deleteObject(deleteObjectRequest);

        } catch (AmazonServiceException e) {

            throw new AmazonServiceException(e.getErrorMessage());

        } catch (SdkClientException e) {

            throw new SdkClientException(e.getMessage());
        }

        return key;
    }
}
