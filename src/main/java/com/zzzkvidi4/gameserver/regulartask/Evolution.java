package com.zzzkvidi4.gameserver.regulartask;

import com.zzzkvidi4.gameserver.CriminalComparator;
import com.zzzkvidi4.gameserver.evolution.GreyCode;
import com.zzzkvidi4.gameserver.model.Criminal;
import com.zzzkvidi4.gameserver.model.CharacterWithTargetValue;
import com.zzzkvidi4.gameserver.model.GameCharacter;
import com.zzzkvidi4.gameserver.service.CriminalService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Evolution {
    public static final int POPULATION_SIZE = 1000;

    public static final double MUTATION_CHANCE = 0.05;

    private Random rnd = new Random(System.currentTimeMillis());

    @Resource(name = "criminalService")
    private CriminalService criminalService;

    private List<Criminal> criminals;

    {
        criminals = criminalService.getAll();
    }

    @Scheduled(cron = "0 0 * * SAT")
    public void evolute(){
        combine(select());
    }

    private int targetFunction(Criminal criminal){
        GameCharacter character = criminal.getGameCharacter();
        return character.getIntellijence() * character.getLeader() + character.getStrength() + character.getCharysma() * character.getSuccess();
    }

    private List<CharacterWithTargetValue> select(){
        return criminals.stream()
                .map(criminal -> new CharacterWithTargetValue(criminal.getGameCharacter(), targetFunction(criminal)))
                .sorted(new CriminalComparator())
                .limit(POPULATION_SIZE)
                .collect(Collectors.toList());
    }

    private List<GameCharacter> combine(List<CharacterWithTargetValue> bestCriminals){
        List<GameCharacter> characters = new ArrayList<>(POPULATION_SIZE);
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            GameCharacter cr1 = bestCriminals.get(rnd.nextInt(POPULATION_SIZE)).getCharacter();
            GameCharacter cr2 = bestCriminals.get(rnd.nextInt(POPULATION_SIZE)).getCharacter();
            characters.add(combine(cr1, cr2));
        }
        return characters;
    }

    private GameCharacter combine(GameCharacter c1, GameCharacter c2){
        StringBuilder gene1 = c1.getGene();
        StringBuilder gene2 = c2.getGene();

        StringBuilder newGene = new StringBuilder();
        int halfGene = (int)(GreyCode.GREY_CODE_LENGTH * 2.5);
        int position = rnd.nextInt(halfGene) + (halfGene / 4);

        newGene
                .append(gene1.subSequence(0, position))
                .append(gene2.subSequence(position + 1, gene2.length() - 1));

        mutate(newGene);

        GameCharacter newCharacter = new GameCharacter();
        newCharacter.setIntellijence(GreyCode.toDecimal(newGene.substring(0, GreyCode.GREY_CODE_LENGTH - 1)));
        newCharacter.setCharysma(GreyCode.toDecimal(newGene.substring(GreyCode.GREY_CODE_LENGTH, 2 * GreyCode.GREY_CODE_LENGTH - 1)));
        newCharacter.setStrength(GreyCode.toDecimal(newGene.substring(2 * GreyCode.GREY_CODE_LENGTH, 3 * GreyCode.GREY_CODE_LENGTH - 1)));
        newCharacter.setLeader(GreyCode.toDecimal(newGene.substring(3 * GreyCode.GREY_CODE_LENGTH, 4 * GreyCode.GREY_CODE_LENGTH - 1)));
        newCharacter.setSuccess(GreyCode.toDecimal(newGene.substring(4 * GreyCode.GREY_CODE_LENGTH, 5 * GreyCode.GREY_CODE_LENGTH - 1)));
        return newCharacter;
    }

    private void mutate(StringBuilder gene){
        if (rnd.nextDouble() < MUTATION_CHANCE) {
            int mutationPosition = rnd.nextInt(gene.length());
            if (gene.charAt(mutationPosition) == '1') {
                gene.replace(mutationPosition, mutationPosition + 1, "0");
            } else {
                gene.replace(mutationPosition, mutationPosition + 1, "1");
            }
        }
    }
}
