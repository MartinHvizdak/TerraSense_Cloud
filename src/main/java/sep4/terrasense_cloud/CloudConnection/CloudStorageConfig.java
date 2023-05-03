package sep4.terrasense_cloud.CloudConnection;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudStorageConfig {
    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }
}

