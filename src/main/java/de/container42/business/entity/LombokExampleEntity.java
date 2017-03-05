package de.container42.business.entity;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by edi on 05.03.17.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(
                name = LombokExampleEntity.ALL_ENTITIES,
                query = "select e from LombokExampleEntity e"
        )
})
@Data
public class LombokExampleEntity {
    public static final String ALL_ENTITIES = "ALL_ENTITIES";

    @Id
    @GeneratedValue
    private long id;

    private String data;
}
