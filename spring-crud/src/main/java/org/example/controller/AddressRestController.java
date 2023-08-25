package org.example.controller;

import org.example.dto.AddressDto;
import org.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/address")
public class AddressRestController {
    @Autowired
    private AddressService service;
    @GetMapping
    public List<AddressDto> findAddresses(){
        return service.getAddresses();
    }
    @GetMapping("/{id}")
    public AddressDto findAddressById(@PathVariable Long id){
        return service.getAddressesById(id);
    }
    @PostMapping
    public AddressDto saveAddress(@RequestBody AddressDto addressDto){
        Long id = addressDto.getAddressPersonDto().getId();
        return service.saveAddress(addressDto, id);
    }
    @PatchMapping
    public AddressDto updateAddress(@RequestBody AddressDto addressDto){
        Long id = addressDto.getId();
        return service.updateAddress(addressDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id){
        service.deleteAddress(id);
    }
}
