package com.example.orm_elite_driving_school_system.bo.custom.impl;

import com.example.orm_elite_driving_school_system.bo.custom.InstructorsBO;
import com.example.orm_elite_driving_school_system.bo.custom.exception.DuplicateException;
import com.example.orm_elite_driving_school_system.bo.custom.exception.NotFoundException;
import com.example.orm_elite_driving_school_system.bo.custom.util.EntityDTOConverter;
import com.example.orm_elite_driving_school_system.dao.DAOFactory;
import com.example.orm_elite_driving_school_system.dao.DAOTypes;
import com.example.orm_elite_driving_school_system.dao.custom.InstructorsDAO;
import com.example.orm_elite_driving_school_system.dto.InstructorsDTO;
import com.example.orm_elite_driving_school_system.entity.Instructors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstructorsBOImpl implements InstructorsBO {

    private final InstructorsDAO instructorsDAO = (InstructorsDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<InstructorsDTO> getAllInstructors() throws Exception {
        List<Instructors> instructorsList = instructorsDAO.getAll();
        List<InstructorsDTO> instructorsDTOList = new ArrayList<>();
        for (Instructors instructors : instructorsList) {
            instructorsDTOList.add(converter.getInstructorsDTO(instructors));
        }
        return instructorsDTOList;
    }

    @Override
    public String getLastInstructorId() throws Exception {
        return  instructorsDAO.getLastId();
    }

    @Override
    public boolean saveInstructors(InstructorsDTO t) throws Exception {
        Optional<Instructors> instructors = instructorsDAO.findById(t.getInstructor_id());
        if (instructors.isPresent()) {
            throw new DuplicateException("Instructor already exists");
        }
        return instructorsDAO.save(converter.getInstructorsEntity(t));
    }

    @Override
    public boolean updateInstructors(InstructorsDTO t) throws Exception {
        Optional<Instructors> instructors = instructorsDAO.findById(t.getInstructor_id());
        if (instructors.isEmpty()) {
            throw new NotFoundException("Instructor not found");
        }
        return instructorsDAO.update(converter.getInstructorsEntity(t));
    }

    @Override
    public boolean deleteInstructors(String id) throws Exception {
        Optional<Instructors> instructors = instructorsDAO.findById(id);
        if (instructors.isEmpty()) {
            throw new NotFoundException("Instructor not found");
        }
        return instructorsDAO.delete(id);
    }

    @Override
    public List<String> getAllInstructorIds() throws Exception {
        return instructorsDAO.getAllIds();
    }

    @Override
    public Optional<InstructorsDTO> findByInstructorId(String id) throws Exception {

        Optional<Instructors> instructors = instructorsDAO.findById(id);
        if (instructors.isPresent()) {
            return Optional.of(converter.getInstructorsDTO(instructors.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewInstructorsId() {
        return instructorsDAO.generateNewId();
    }
}
