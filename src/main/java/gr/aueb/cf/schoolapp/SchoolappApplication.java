package gr.aueb.cf.schoolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing                                                                   //(MASTER SWITCH OF ALARM) Must be included! It is the "master button" for activating the auditing across the whole spring context in order for the Listeners (@CreatedDate, @LastModifiedDate) in AbstractEntity to function in reality.
public class SchoolappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolappApplication.class, args);
    }

}
