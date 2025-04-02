package by.bsu.detailstorage.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Detail> details;
}
