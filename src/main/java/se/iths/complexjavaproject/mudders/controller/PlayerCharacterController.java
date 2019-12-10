package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.InvalidJsonDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;
import se.iths.complexjavaproject.mudders.service.PlayerCharacterService;
import se.iths.complexjavaproject.mudders.service.TravelService;
import se.iths.complexjavaproject.mudders.service.World;

import java.util.Scanner;

@Service
@RestController
@NoArgsConstructor
@RequestMapping("/player")
public class PlayerCharacterController {

    @Autowired
    private PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    PlayerCharacterService playerCharacterService;

    @Autowired
    World world;

    @Autowired
    TravelService travelService;

    @GetMapping(path = "/all")
    public ResponseEntity getAllPlayers() {
        try{
            Iterable<PlayerCharacter> findAllPlayers = playerCharacterRepository.findAll();
            return ResponseEntity.ok().body(findAllPlayers);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "combatattack")
    public String playerCombatChoiceOne(){
        try {
            PlayerCharacter playerCharacter = new PlayerCharacter();
            playerCharacter.setCombatChoice("1");
            return playerCharacter.getCombatChoice();
        }catch (Exception e){
            }
        return "1";
    }

    @GetMapping(path = "combatescape")
    public String playerCombatChoiceZero(){
        try {
            PlayerCharacter playerCharacter = new PlayerCharacter();
            playerCharacter.setCombatChoice("0");
            return playerCharacter.getCombatChoice();
        }catch (Exception e){
        }
        return "0";
    }


    @PostMapping(path = "/add")
    public ResponseEntity addNewPlayerCharacter (@RequestBody String characterName){
        try {
            PlayerCharacterModel playerCharacterModel = playerCharacterRepository
                    .save(PlayerCharacterService.convertToEntity(characterName))
                    .toModel();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(playerCharacterModel);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping(path = "/find")
    public ResponseEntity getTravelPlayerByName(@RequestBody String characterName) {
        try {
            PlayerCharacterModel playerCharacterModel = travelService.travel(characterName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(playerCharacterModel);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }


    @PostMapping(value = "/fightoption", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse combat(@RequestBody ClientResponse clientResponse) {

        System.out.println(clientResponse.getCharacterName());

        System.out.println(clientResponse.getOption());

        if (clientResponse.empty()) {
            throw new InvalidJsonDataException();
        }

        return new ServerResponse("attack, " + clientResponse.getCharacterName()
                + ", " +clientResponse.getOption(), false, true, false, true);

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete")
    public void removePlayer(@RequestParam String characterName){
        try{
            playerCharacterRepository.deletePlayerCharacterByCharacterName(characterName);

        }catch (Exception e){
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
