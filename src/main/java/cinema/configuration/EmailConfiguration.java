package cinema.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import java.util.Properties;

@Slf4j
@Configuration
public class EmailConfiguration {

    public Properties getProperty(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        return prop;
    }


        final String from = "from";
        final String password = "password";



        public Session getSession(){
        Session session = Session.getInstance(getProperty(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPassAuth() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        return session;
        }



}

