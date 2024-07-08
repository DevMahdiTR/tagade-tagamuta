package tagarde.config;

public final class APIRouters {

    public static final String BASE_PATH = "http://localhost:";
    public static final String PORT = "8081";
    private static final String BASE_API_PATH = "api";
    private static final String CURRENT_VERSION = "v1";
    public static final String AUTH_ROUTER = BASE_API_PATH + "/" + CURRENT_VERSION + "/auth";
    public static String getConfirmationURL(String confirmationToken) {
        return BASE_PATH + PORT +"/" + AUTH_ROUTER + "/confirm?token=" + confirmationToken;
    }

    private APIRouters() {}
}
