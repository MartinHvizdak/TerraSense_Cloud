package sep4.terrasense_cloud.CloudConnection;
import sep4.terrasense_cloud.CloudConnection.Message;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class Writer {

    @Autowired
    private Storage storage;
    @Value("${file.storage}")
    private Resource localFilePath;

    @RequestMapping(path = {"/write-file/{fileName}"}, method={RequestMethod.GET})
    public Message writeFileToBucket(@PathVariable(name="fileName")String fileName) throws Exception{
        BlobId blobId=BlobId.of("terrasense_readings",fileName);
        BlobInfo blobInfo=BlobInfo.newBuilder(blobId).build();
        File fileToRead = new File(localFilePath.getFile(),fileName);
        byte[] data= Files.readAllBytes(Paths.get(fileToRead.toURI()));
        storage.create(blobInfo,data);
        Message message=new Message();
        message.setContents(new String(data));
        return message;
    }

}
