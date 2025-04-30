package by.bsu.detailstorage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detail")
@Data
public class Detail implements DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "amount", nullable = false)
    private int amount;
}
