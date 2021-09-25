package br.com.systemsgs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction implements Serializable {

    @Id
    private Integer oderId;
    private int userId;
    private int amount;

}
