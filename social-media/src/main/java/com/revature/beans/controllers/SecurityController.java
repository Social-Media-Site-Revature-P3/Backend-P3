package com.revature.beans.controllers;


import com.revature.beans.services.SecurityService;
import com.revature.models.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/security-question")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SecurityController {
    private SecurityService service;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.service = securityService;
    }
    @RequestMapping(value = "/{securityQuestionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody SecurityQuestion findSecurityQuestion(@PathVariable Integer securityQuestionId) {
        Optional<SecurityQuestion> optionalSecurityQuestion= service.findSecurityQuestion(securityQuestionId);
        return optionalSecurityQuestion.get();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<SecurityQuestion> getSecurityQuestionsByUserId(@PathVariable Integer userId) {
        return service.readByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<SecurityQuestion> readAllSecurityQuestions() {
        return service.readAllSecurityQuestions();
    }

    @RequestMapping(value = "/userId/{userId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public SecurityQuestion getByQuestionAndAnswer(@PathVariable Integer userId, @RequestBody SecurityQuestion securityQuestion) {
        try{
            return service.readByQuestionAndAnswer(securityQuestion, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public SecurityQuestion createSecurityQuestion(@RequestBody SecurityQuestion securityQuestion) {
        return service.createSecurityQuestion(securityQuestion);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateSecurityQuestion(@RequestBody SecurityQuestion securityQuestion) {
        service.updateSecurityQuestion(securityQuestion);
    }

    @RequestMapping(value = "/{pastOrderId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePastOrder(@PathVariable(name = "pastOrderId") Integer id) {
        service.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteSecurityQuestionByQuestion(@RequestBody SecurityQuestion securityQuestion) {
        service.deleteBySecurityQuestion(securityQuestion);
    }
}