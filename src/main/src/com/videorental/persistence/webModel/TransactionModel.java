package com.videorental.persistence.webModel;

/**
 * Created by vegasjm on 02/08/2016.
 */
public class TransactionModel {

        private Long id;

        private String customer;

        private String movie;

        private Long nDays;

        private Long nExtraDays;

        private Long price;

        private Long priceInTime;

        private Long priceInExtraTime;

        private Boolean isSettled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Long getnDays() {
        return nDays;
    }

    public void setnDays(Long nDays) {
        this.nDays = nDays;
    }

    public Long getnExtraDays() {
        return nExtraDays;
    }

    public void setnExtraDays(Long nExtraDays) {
        this.nExtraDays = nExtraDays;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getIsSettled() {
        return isSettled;
    }

    public void setIsSettled(Boolean isSettled) {
        this.isSettled = isSettled;
    }

    public Long getPriceInTime() {
        return priceInTime;
    }

    public void setPriceInTime(Long priceInTime) {
        this.priceInTime = priceInTime;
    }

    public Long getPriceInExtraTime() {
        return priceInExtraTime;
    }

    public void setPriceInExtraTime(Long priceInExtraTime) {
        this.priceInExtraTime = priceInExtraTime;
    }
}
