package wang.diff.scaffold.controller.common;

import diff.wang.user.server.controller.ObjectStoreApi;
import diff.wang.user.server.controller.model.GetBatchUrlRequest;
import diff.wang.user.server.controller.model.ObjectStoreBatchDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Tag(name = "object-store")
public class ObjectStoreController implements ObjectStoreApi {
    @Override
    public ResponseEntity<ObjectStoreBatchDTO> getBatchUrl(GetBatchUrlRequest getBatchUrlRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> getDownload(String filename) {
        return null;
    }

    @Override
    public ResponseEntity<ObjectStoreBatchDTO> uploadMultipart(List<MultipartFile> files) {
        return null;
    }
}
