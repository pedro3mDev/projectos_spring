/*
package grupo3mtech.ao.api_rest.controllers;

import grupo3mtech.ao.api_rest.dtos.ProdutsRecordsDto;
import grupo3mtech.ao.api_rest.models.ProdutModel;
import grupo3mtech.ao.api_rest.repositorys.ProdutRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restfull")
public class ProdutsControllers {
    @Autowired
    ProdutRepository produtRepository;

    @PostMapping("/produts")
    public ResponseEntity<ProdutModel> saveProdut(@RequestBody @Valid ProdutsRecordsDto produtsRecordsDto){
        var produtModel = new ProdutModel();
        BeanUtils.copyProperties(produtsRecordsDto, produtModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtRepository.save(produtModel));
    }


}


 */