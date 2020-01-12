package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "player_character")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PlayerCharacter implements Serializable {

    private static final long serialVersionUID = 2982112410056449932L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "character_name", unique = true)
    private String characterName;

    @Column(name = "experience")
    private int experience = 0;

    @Column(name = "level")
    private int level = 1;

    @Column(name = "health")
    private int health = 20;

    @Column(name = "damage")
    private int damage = 1;

    @Column(name = "currency")
    private int currency = 100;

    @Column(name = "max_health")
    private int maxHealth = 20;

    private boolean inCombat = false;

    @OneToOne
    @JoinColumn(name = "town_id")
    private Town currentTown;

    @OneToMany(mappedBy = "playerCharacter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemAmount> itemAmounts = new HashSet<>();

    /*TODO: Add one to one relation with user to keep track of owner
    @Column(name = "user_id")
    private Long id;
    */

    public PlayerCharacterModel toModel() {
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.setCharacterName(getCharacterName());
        playerCharacterModel.setExperience(getExperience());
        playerCharacterModel.setLevel(getLevel());
        playerCharacterModel.setHealth(getHealth());
        playerCharacterModel.setCurrentTown(getCurrentTown());
        playerCharacterModel.setDamage(getDamage());
        playerCharacterModel.setCurrency(getCurrency());

        // TODO: Lägga till itemAmounts i modellen? Hur?

        return playerCharacterModel;
    }

}
