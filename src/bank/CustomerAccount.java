/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author agurtxertudi
 */
public class CustomerAccount {
    private Long customer_id;
    private Long account_id;
    /**
     * @return the customers_id
     */
    public Long getCustomers_id() {
        return customers_id;
    }

    /**
     * @param customers_id the customers_id to set
     */
    public void setCustomers_id(Long customers_id) {
        this.customers_id = customers_id;
    }

    /**
     * @return the accounts_id
     */
    public Long getAccounts_id() {
        return accounts_id;
    }

    /**
     * @param accounts_id the accounts_id to set
     */
    public void setAccounts_id(Long accounts_id) {
        this.accounts_id = accounts_id;
    }
    private Long customers_id;
    private Long accounts_id;
    
}
