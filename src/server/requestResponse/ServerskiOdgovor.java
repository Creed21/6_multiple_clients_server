/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.requestResponse;

import java.io.Serializable;
import java.util.List;
import server.model.Model;
import server.model.Student;

/**
 *
 * @author Korisnik
 */
public class ServerskiOdgovor implements Serializable  {
    private String message;
    private boolean success;
    private Model result;
    private List<Model> resultList;
    private List<Student> resultStudents;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(String message, boolean success, Model result, List<Model> resultList) {
        this.message = message;
        this.success = success;
        this.result = result;
        this.resultList = resultList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Model getResult() {
        return result;
    }

    public void setResult(Model result) {
        this.result = result;
    }

    public List<Model> getResultList() {
        return resultList;
    }

    public void setResultList(List<Model> resultList) {
        this.resultList = resultList;
    }

    public List<Student> getResultStudents() {
        return resultStudents;
    }

    public void setResultStudents(List<Student> resultStudents) {
        this.resultStudents = resultStudents;
    }
    
    
    
    
}
