package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.DBHelper;
import com.zzzkvidi4.gameserver.model.GameCharacter;
import com.zzzkvidi4.gameserver.response.Error;
import com.zzzkvidi4.gameserver.validator.Validator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class HibernateCharacterService implements CharacterService {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<GameCharacter> getAll() {
        logger.info("Started fetching all objects: GameCharacter");
        List<GameCharacter> result = new ArrayList<>();
        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session.createQuery("from GameCharacter", GameCharacter.class).list();
                logger.info("Successfully fetched objects: " + result.toString());
                transaction.commit();
            }
            catch(Exception e) {
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public GameCharacter findById(Integer id) {
        logger.info("Started fetching object by id: GameCharacter");
        GameCharacter result = null;
        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session
                        .createQuery("from GameCharacter where id = :id", GameCharacter.class)
                        .setParameter("id", id)
                        .getSingleResult();
                logger.info("Successfully fetched object: " + result.toString());
                transaction.commit();
            }
            catch(Exception e) {
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public List<Error> create(GameCharacter entity) {
        return null;
    }

    @Override
    public List<Error> createCharacter(int intellijence, int charysma, int strength, int leader, int success) {
        List<Error> errors = Validator.validateCharacter(intellijence, charysma, strength, leader, success);

        if (errors.size() != 0) {
            return errors;
        }

        GameCharacter character = new GameCharacter();
        character.setIntellijence(intellijence);
        character.setCharysma(charysma);
        character.setStrength(strength);
        character.setLeader(leader);
        character.setSuccess(success);

        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.save(character);
                transaction.commit();
            }
            catch (Exception e) {
                errors.add(new Error("database", e.toString()));
                transaction.rollback();
            }
        }
        return errors;
    }
}
