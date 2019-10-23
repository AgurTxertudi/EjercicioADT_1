/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author agurtxertudi
 */
public class Movement implements Serializable {
    
    private Long id;
    private Double amount;
    private Double balance;
    private String description;
    private Timestamp timeStamp;
    private Long account_id;
    
    public Movement(){
        
    }
    
    public Movement(Long id, Double amount, Double balance, String description, Timestamp timeStamp, Long account_id)
    {
        this.id = id;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.timeStamp = timeStamp;
        this.account_id = account_id;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the account_id
     */
    public Long getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }
    
}