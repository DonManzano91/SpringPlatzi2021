package com.manzano.market.dominio;

import com.manzano.market.dominio.PurchaseItem;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {

    private int idPurchase;
    private String idClient; //este tambien habria que moverle
    private LocalDateTime date;
    private String paymentMethod;
    private String comments;
    private String state;
    /*Cliente no es necesario dado que ya tiene la anotación definida en persistencia*/
    private List<PurchaseItem> item;

    public Integer getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<PurchaseItem> getItem() {
        return item;
    }

    public void setItem(List<PurchaseItem> item) {
        this.item = item;
    }
}
