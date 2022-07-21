package entitites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity (name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pname;
    private String team;
    private String position;
    private Integer age;
    private String league;
    private boolean isactive;
    private String nationality;
}
