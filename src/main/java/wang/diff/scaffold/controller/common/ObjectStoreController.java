package wang.diff.scaffold.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wang.diff.scaffold.common.component.MinIoTools;
import wang.diff.scaffold.controller.ObjectStoreApi;
import wang.diff.scaffold.controller.model.GetBatchUrlRequest;
import wang.diff.scaffold.controller.model.ObjectStoreBatchDTO;

import java.util.List;

@RestController
@Tag(name = "object-store")
public class ObjectStoreController implements ObjectStoreApi {

    @Value("${minio.bucket-name}")
    private String bucket;

    @Resource
    private MinIoTools minIoTools;

    @Resource
    private HttpServletResponse httpServletResponse;

    @Override
    public ResponseEntity<ObjectStoreBatchDTO> getBatchUrl(GetBatchUrlRequest getBatchUrlRequest) {
        List<String> uploadObjectUrls = minIoTools.getUploadObjectUrls(bucket, getBatchUrlRequest.getFilenames());
        ObjectStoreBatchDTO ot =  new ObjectStoreBatchDTO();
        ot.setUrls(uploadObjectUrls);
        return ResponseEntity.ok(ot);
    }

    @Override
    public ResponseEntity<Void> getDownload(String filename) {
        minIoTools.download(bucket,filename, httpServletResponse);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ObjectStoreBatchDTO> uploadMultipart(List<MultipartFile> files) {
        MultipartFile[] fileArray = new MultipartFile[files.size()];
        MultipartFile[] array = files.toArray(fileArray);
        // MultipartFile[] filesArray = null;
        // files.stream().forEach(x->filesArray.);;
        List<String> uploadFileBatch = minIoTools.uploadFileBatch(bucket, array);
        ObjectStoreBatchDTO objectStoreMultipartDTO = new ObjectStoreBatchDTO();
        objectStoreMultipartDTO.setUrls(uploadFileBatch);
        return ResponseEntity.ok(objectStoreMultipartDTO);
    }
}
