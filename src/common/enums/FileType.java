package common.enums;

public enum FileType {
    PNG("png");

    String fileName;

    FileType(String fileName) {
        this.fileName = fileName;
    }

    public String fileName() {
        return fileName;
    }

    public String getExt() {
        return "." + fileName;
    }
}
