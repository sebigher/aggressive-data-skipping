package ovgu.aggressivedataskipping.livy;

import org.apache.livy.LivyClient;
import org.apache.livy.LivyClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class LivyConfig {

    @Value("${spark.livy.url}")
    private String livyUrl;

    @Value("${spark.livy.jarpath}")
    private String tempJarPath;

    @Value("${spark.livy.classpath}")
    private String[] jarClassPaths;

    @Bean
    public LivyClientWrapper livyClient() throws IOException, URISyntaxException {
        LivyClient client = new LivyClientBuilder()
                .setURI(new URI(livyUrl))
                .build();
        return new LivyClientWrapper(client);
    }

    @Bean
    public JarCreator jarCreator() {
        return new JarCreator(jarClassPaths, tempJarPath);
    }

}
