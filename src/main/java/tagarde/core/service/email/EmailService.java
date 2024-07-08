package tagarde.core.service.email;

import java.util.Map;

public interface EmailService {


    public void sendMail(String to, String subject, String templateName, Map<String, Object> model);
}


