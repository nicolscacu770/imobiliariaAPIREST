package com.example.springapp.controllers;

import com.example.springapp.entities.Cliente;
import com.example.springapp.entities.Propietario;
import com.example.springapp.responses.ResponseHandler;
import com.example.springapp.services.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propietario")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try{
            List<Propietario> result = propietarioService.findAll();
            if(result != null){
                return ResponseHandler.generateResponse("Success",HttpStatus.OK,result);
            }else{
                return ResponseHandler.generateResponse("Not found",HttpStatus.NOT_FOUND,result);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Propietario propietario = propietarioService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, propietario);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Propietario propietario){
        try{
            Propietario result = propietarioService.save(propietario);

            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            Propietario propietario = propietarioService.findById(id);
            if(propietario != null){
                propietarioService.delete(propietario);

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, propietario);
            }
            return ResponseHandler.generateResponse("Succes Propietario",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
