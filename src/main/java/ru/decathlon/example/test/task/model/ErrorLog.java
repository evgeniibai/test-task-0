package ru.decathlon.example.test.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import ru.decathlon.example.test.task.util.ExceptionFormatterUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "error_log")
@NoArgsConstructor
@Getter
@Setter
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id")
    private Integer errorId;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "description", length = 2048)
    private String description;

    @Autowired(required = false)
    public ErrorLog(Exception ex) {

        this.description = ExceptionFormatterUtil.format(ex);
    }
}
