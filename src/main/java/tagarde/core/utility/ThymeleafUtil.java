package tagarde.core.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;


@Component
public class ThymeleafUtil {
    private static TemplateEngine templateEngine;
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        ThymeleafUtil.templateEngine = applicationContext.getBean(TemplateEngine.class);
    }

    public static String processEmailTemplate(String templateName, Map<String, Object> model) {
        Context context = new Context();
        context.setVariables(model);
        return templateEngine.process(templateName, context);
    }
}
