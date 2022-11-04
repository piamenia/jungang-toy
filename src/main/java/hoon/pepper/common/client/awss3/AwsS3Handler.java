package hoon.pepper.common.client.awss3;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import hoon.pepper.common.wrapper.ResultResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AwsS3Handler {

    private final AwsS3Service awsS3Service;

    public AwsS3Handler(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    /**
     * 파일 업로드
     * @param mf 업로드 대상 파일
     * @return 성공시 저장 key 리턴
     * @throws Exception
     */
    public PutObjectResult upload(@RequestBody MultipartFile mf) {

        return awsS3Service.uploadMultiPart(mf);
    }

    /**
     * 파일 다운로드
     * @param key 다운받을 파일의 key
     * @return 파일 스트림 -> 가공 필요!
     * @throws Exception
     */
    public ResultResponse<String> download(String key) {

        S3Object s3 = awsS3Service.getFile(key);
        return new ResultResponse<>(s3.getKey());
    }

    /**
     * 파일 다운로드 url
     * @param key 다운받을 파일의 key
     * @return 다운 url 리턴
     * @throws Exception
     */
    public String makeUrl(String key) {

        return awsS3Service.getDownloadUrl(key);
    }

    /**
     * 파일 복사
     * @param oldKey 원 파일의 key
     * @param newKey 복사될 파일의 key
     * @return 성공시 저장 key 리턴
     * @throws Exception
     */
    public ResultResponse<String> copy(String oldKey, String newKey) {

        return new ResultResponse<>(awsS3Service.copy(oldKey, newKey));
    }

    /**
     * 파일 삭제
     * @param key 삭제할 파일의 key 값
     * @return 성공시 key 리턴
     * @throws Exception
     */
    public ResultResponse<String> delete(String key) {

        String res = awsS3Service.delete(key);
        return new ResultResponse<>(res);
    }
}
