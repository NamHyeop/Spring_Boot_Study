package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String upLoadFileName, String storeFileName) {
        this.uploadFileName = upLoadFileName;
        this.storeFileName = storeFileName;
    }
}
