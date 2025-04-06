package durgaproject.jornalapp1.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "jornalEntry")
@Data
@NoArgsConstructor
public class JornalEntry {
    @Id
    private ObjectId id;
    private String titel;
    private String content;
    private LocalDateTime date;

}
