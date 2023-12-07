package wang.diff.scaffold.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@ConditionalOnProperty(name = "minio.endpoint", havingValue = "http://192.168.226.136:9000")
@Configuration
public class MinIoConfig {
    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.endpoint}")
    private String endpoint;


    @Bean
    MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey,secretKey).build();
    }

}