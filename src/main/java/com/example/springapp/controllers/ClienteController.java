package com.example.springapp.controllers;

import com.example.springapp.entities.Cliente;
import com.example.springapp.responses.ResponseHandler;
import com.example.springapp.services.ClienteService;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try{
            List<Cliente> result = clienteService.findAll();
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
            Cliente cliente = clienteService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, cliente);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Cliente cliente){
        try{
            Cliente result = clienteService.save(cliente);

            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            Cliente cliente = clienteService.findById(id);
            if(cliente != null){
                clienteService.delete(cliente);

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, cliente);
            }
            return ResponseHandler.generateResponse("Succes Cliente",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
