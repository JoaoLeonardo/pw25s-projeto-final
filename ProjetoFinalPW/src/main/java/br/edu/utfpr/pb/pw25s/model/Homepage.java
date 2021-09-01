package br.edu.utfpr.pb.pw25s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Homepage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String texto;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DestaqueHomepage",
            joinColumns= @JoinColumn(name = "homepage_id", referencedColumnName= "id"),
            inverseJoinColumns= @JoinColumn(name = "destaque_id", referencedColumnName= "id")
    )
    private List<Destaque> destaques;

}
