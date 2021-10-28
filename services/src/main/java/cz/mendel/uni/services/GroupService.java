package cz.mendel.uni.services;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.repositories.GroupRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;

    public Group findById(long id){
        return groupRepository.findById(id).get();
    }
    public Group save(Group group){
        return groupRepository.save(group);
    }
    public List<Group> findAll(){
        return groupRepository.findAll();
    }
    public void deleteById(long id){
        groupRepository.deleteById(id);
    }
}
