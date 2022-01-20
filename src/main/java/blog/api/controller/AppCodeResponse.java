package blog.api.controller;

public enum AppCodeResponse {
    SUCCESS("blog-200", "success"),
    INTERNAL_SERVER_ERROR("blog-500", "internal server error"),
    NOT_FOUND("blog-404", "not found"),
    BAD_REQUEST("blog-400", "bad request");

    private String code;
    private String message;

    AppCodeResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
