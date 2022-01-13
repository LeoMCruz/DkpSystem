//package com.example.Anarchy.rest;
//
//import com.example.Anarchy.domain.repository.UserRepository;
//import com.example.Anarchy.dto.ToonRequest;
//import com.example.Anarchy.dto.ToonResponse;
//import com.example.Anarchy.dto.UserRequest;
//import com.example.Anarchy.service.UserService;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
//@RestController
//public class UserApi {
//    private final UserService userService;
//    private final UserRepository userRepository;
//
//    public UserApi(UserService userService, UserRepository userRepository) {
//        this.userService = userService;
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping(value = "/api/v1/user", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<?> save(@RequestBody UserRequest userRequest){
//        //CONFERE SE O ID EXISTE E CRIA
//        if(toonRequest.getId() != null)
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(userService.salvarUser(userRequest));
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(userService.salvarToon(toonRequest));
//
//    }
//    @GetMapping(value = "/api/v1/user", produces = "application/json")
//    public ResponseEntity<List<ToonResponse>> findAll(Pageable pageable){
//        return ResponseEntity.ok(userService.buscarTodos(pageable));
//    }
//
//    @GetMapping(value = "/api/v1/user/{id}", produces = "application/json")
//    public ResponseEntity<?> findById(@PathVariable Long id){
//        var toonResponse = userService.buscarById(id);
//        if(toonResponse == null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(toonResponse);
//    }
//}
