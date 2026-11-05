package com.ssolution.cab.service.service;

import com.ssolution.cab.service.entity.Cab;
import com.ssolution.cab.service.repository.CabRepository;
import com.ssolution.dtos.enums.CommonStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CabService {
    private CabRepository cabRepository;

    public Cab saveCab(Cab cab){
        return cabRepository.save(cab);
    }

    public List<Cab> getAllCabs(){
        return cabRepository.findAll();
    }

    public Cab getCabById(UUID id){
        return cabRepository.findById(id).orElse(null);
    }

    public void deleteCab(UUID id){
        cabRepository.deleteById(id);
    }

    public boolean existsByRegistrationNumberAndCabStatus(String registrationNumber, CommonStatus commonStatus) {
        return cabRepository.existsByRegistrationNumberAndStatus(registrationNumber, commonStatus);
    }


}
