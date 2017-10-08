package io.github.tiagorgt.vertx.api.entity;

import io.vertx.core.json.JsonObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tiago on 07/10/2017.
 */
@Entity
@Table(name = "funcao_usuario_externo")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_funcao")
    private Integer id;

    @Column(name = "no_funcao")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJsonString(){
        return String.valueOf(JsonObject.mapFrom(this));
    }
}
