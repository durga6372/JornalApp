package durgaproject.jornalapp1.dto;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;

    @NonNull
    private String password;

    private String email;
    public boolean sentimentAnalysis;

}

