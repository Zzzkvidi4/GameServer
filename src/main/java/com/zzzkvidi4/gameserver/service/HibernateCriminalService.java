package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.DBHelper;
import com.zzzkvidi4.gameserver.model.Criminal;
import com.zzzkvidi4.gameserver.model.CriminalPK;
import com.zzzkvidi4.gameserver.model.GameCharacterStatus;
import com.zzzkvidi4.gameserver.response.Error;
import com.zzzkvidi4.gameserver.utils.UnproxyUtils;
import com.zzzkvidi4.gameserver.validator.Validator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("criminalService")
public class HibernateCriminalService implements CriminalService {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public List<Criminal> getAll() {
        logger.info("Started fetching all objects: Criminal");
        List<Criminal> result = new ArrayList<>();
        try(Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session.createQuery("from Criminal", Criminal.class).list();
                logger.info("Successfully fetched objects: " + result.toString());
                transaction.commit();
            }
            catch (Exception e){
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return result
                .stream()
                .map(criminal -> UnproxyUtils.unproxy(criminal, false, false))
                .collect(Collectors.toList());
    }

    @Override
    public Criminal findById(CriminalPK id) {
        logger.info("Started fetching object by id: Criminal");
        Criminal result = null;
        try(Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session
                        .createQuery("from Criminal c " +
                                        "left outer join fetch c.gameCharacter " +
                                        "left outer join fetch c.status " +
                                        "where c.id = :id and c.gameCharacterId = :gameCharacterId", Criminal.class)
                        .setParameter("id", id.getId())
                        .setParameter("gameCharacterId", id.getGameCharacterId())
                        .getSingleResult();
                logger.info("Successfully fetched object: " + result.toString());
                transaction.commit();
            }
            catch (Exception e){
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return UnproxyUtils.unproxy(result, true, true);
    }

    @Override
    public List<Error> create(Criminal entity) {
        List<Error> errors = Validator.validateCriminal(entity);

        try(Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
            }
            catch (Exception e) {
                errors.add(new Error("sql", e.toString()));
                transaction.rollback();
            }
        }
        return errors;
    }
}
