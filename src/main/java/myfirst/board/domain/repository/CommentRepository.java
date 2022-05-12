package myfirst.board.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentRepository {

    @Autowired
    EntityManager em;


}
