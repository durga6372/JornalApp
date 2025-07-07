package durgaproject.jornalapp1.entity;
import durgaproject.jornalapp1.Enum.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "jornalEntry")
@Data
@NoArgsConstructor
public class JornalEntry {
    @Id
    private ObjectId id;
    private String titel;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
