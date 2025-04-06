package durgaproject.jornalapp1.confogure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://durgasankarputhal:Durga%40123@cluster0.ewvhs.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
    }// Replace with your MongoDB connection string

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "journaldb");
    }
}

