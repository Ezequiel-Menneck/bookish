package com.menneck.bookish.Service;

import com.menneck.bookish.DTO.SellerDTO;
import com.menneck.bookish.Model.Seller;
import com.menneck.bookish.Repository.SellerRepository;
import com.menneck.bookish.Service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public Seller findById(Integer id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);
        return optionalSeller.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Transactional
    public void create(SellerDTO sellerDTO) { sellerRepository.save(createSeller(sellerDTO)); }

    private Seller createSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();

        seller.setName(sellerDTO.getName());
        seller.setPhone(sellerDTO.getPhone());
        seller.setComission(sellerDTO.getComission());

        return seller;
    }

    public Seller updateSeller(Integer id, SellerDTO sellerDTO) {
        try {
            Seller entity = sellerRepository.getReferenceById(id);
            updateData(entity, sellerDTO);
            return sellerRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Seller entity, SellerDTO sellerDTO) {
        entity.setName(sellerDTO.getName());
        entity.setPhone(sellerDTO.getPhone());
        entity.setComission(sellerDTO.getComission());
    }
}
