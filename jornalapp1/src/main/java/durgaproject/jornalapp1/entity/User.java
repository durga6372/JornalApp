package durgaproject.jornalapp1.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true )
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JornalEntry> jornalEntry= new ArrayList<>();
    private  String Email;
    private boolean sentimentAnalysis;
    private List<String> rolls=new ArrayList<>();

}
