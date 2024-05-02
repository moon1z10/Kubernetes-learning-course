package hodlene.k8s.todo_mysql_web_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;

    private boolean isDone;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date targetDate;

    private String username;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        return id == ((Todo) obj).id;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]",
                id, username, description, targetDate, isDone);
    }
}

