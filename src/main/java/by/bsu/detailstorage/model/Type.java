package by.bsu.detailstorage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type")
@Data
public class Type implements DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;
}
