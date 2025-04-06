package durgaproject.jornalapp1.repo;

import durgaproject.jornalapp1.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class userReposiratoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserforSA(){
        Query query= new Query();
        query.addCriteria(Criteria.where("Email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
