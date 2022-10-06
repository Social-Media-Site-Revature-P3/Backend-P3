package com.revature.beans.controllers;


import com.revature.beans.services.SecurityService;
import com.revature.models.SecurityQuestion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/security-question")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SecurityController {
    private SecurityService service;

    public SecurityController(SecurityService securityService) {
        this.service = securityService;
    }

    //Gets Security Question/Answer by ID
    @RequestMapping(value = "/{securityQuestionId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody SecurityQuestion findSecurityQuestion(@PathVariable Integer securityQuestionId) {
        Optional<SecurityQuestion> optionalSecurityQuestion= service.findSecurityQuestion(securityQuestionId);
        return optionalSecurityQuestion.get();
    }

    //Gets all security questions belonging to a user
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<SecurityQuestion> getSecurityQuestionsByUserId(@PathVariable Integer userId) {
        return service.readByUserId(userId);
    }



    //Gets all security Questions (probably useless).
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<SecurityQuestion> readAllSecurityQuestions() {
        return service.readAllSecurityQuestions();
    }

    //Checks if User inputs the correct information for password reset.  Since 2 users could technically have the same question and answer, need to check userId as well.
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
    public SecurityQuestion updateSecurityQuestion(@RequestBody SecurityQuestion securityQuestion) {
        return service.updateSecurityQuestion(securityQuestion);
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