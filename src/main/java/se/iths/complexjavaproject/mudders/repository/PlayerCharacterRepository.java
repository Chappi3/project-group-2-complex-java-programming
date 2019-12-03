package se.iths.complexjavaproject.mudders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, Long>{

    PlayerCharacter findByCharacterName(String characterName);

}
