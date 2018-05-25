package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.DBHelper;
import com.zzzkvidi4.gameserver.model.GameCharacterStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HibernateCharacterStatusServiceTest {
    private HibernateCharacterStatusService statusService = new HibernateCharacterStatusService();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void getAllTest(){
        List<GameCharacterStatus> expectedCharacterStatuses = new ArrayList<>();
        try (Session session = DBHelper.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                expectedCharacterStatuses = session
                        .createQuery("from GameCharacterStatus", GameCharacterStatus.class)
                        .list();
                transaction.commit();
            }
            catch (Exception e) {
                transaction.rollback();
            }
        }
        logger.info(expectedCharacterStatuses.toString());
        List<GameCharacterStatus> characterStatuses = statusService.getAll();
        logger.info(characterStatuses.toString());
        assertEquals(expectedCharacterStatuses, characterStatuses);
    }

    @Test
    public void getByIdTest(){

    }
}
