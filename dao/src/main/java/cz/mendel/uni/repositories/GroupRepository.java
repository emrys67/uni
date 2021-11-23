package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();
}
