package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();

    @Transactional
    @Modifying
    @Query("update Group c set c.name = ?1 where c.id = ?2")
    void update(String name, long id);
}
