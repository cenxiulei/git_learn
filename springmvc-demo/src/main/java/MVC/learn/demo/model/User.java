package MVC.learn.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`user`")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

}

