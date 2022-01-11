package com.project.isa.service.impl;

import com.project.isa.model.User;
import com.project.isa.model.VacationHome;
import com.project.isa.repository.VacationHomeRepository;
import com.project.isa.request.VacationHomeRequest;
import com.project.isa.service.UserService;
import com.project.isa.service.VacationHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationHomeServiceImpl implements VacationHomeService {

    @Autowired
    private VacationHomeRepository vacationHomeRepository;

    @Autowired
    private UserService userService;

    @Override
    public VacationHome findById(Long id) throws AccessDeniedException {
        return vacationHomeRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<VacationHome> findAll() {
        return vacationHomeRepository.findAll();
    }

    @Override
    public VacationHome save(VacationHomeRequest vacationHomeRequest) {
        VacationHome vh = new VacationHome();

        vh.setVacationHomeName(vacationHomeRequest.getVacationHomeName());
        vh.setVacationHomeLocation(vacationHomeRequest.getVacationHomeLocation());
        vh.setVacationHomePrice(vacationHomeRequest.getVacationHomePrice());
        vh.setVacationHomeReview(vacationHomeRequest.getVacationHomeReview());
        User vacationHomeOwner = userService.findById(vacationHomeRequest.getVacationHomeOwner());
        vh.setVacationHomeOwner(vacationHomeOwner);


        return vacationHomeRepository.save(vh);
    }
}
