package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.DBHelper;
import com.zzzkvidi4.gameserver.model.GameCharacterStatus;
import com.zzzkvidi4.gameserver.response.Error;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Service("statusService")
@Transactional
public class HibernateCharacterStatusService implements CharacterStatusService {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<GameCharacterStatus> getAll() {
        logger.info("Started fetching objects: GameCharacterStatuses");
        List<GameCharacterStatus> result = new ArrayList<>();
        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session.createQuery("from GameCharacterStatus", GameCharacterStatus.class).list();
                logger.info("Fetched objects: " + result.toString());
                transaction.commit();
            }
            catch (Exception e){
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public GameCharacterStatus findById(Integer id) {
        logger.info("Started fetching object by id: GameCharacterStatus");
        GameCharacterStatus result = null;
        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                result = session
                        .createQuery("from GameCharacterStatus where id = :id", GameCharacterStatus.class)
                        .setParameter("id", id)
                        .getSingleResult();
                logger.info("Fetched object: " + result.toString());
                transaction.commit();
            }
            catch (Exception e){
                logger.warning("Exception occurred: " + e.toString());
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public List<Error> create(GameCharacterStatus entity) {
        return null;
    }
}
