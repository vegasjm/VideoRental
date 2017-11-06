package com.videorental.model;

import javax.persistence.Column;

/**
 * Created by vegasjm on 05/11/2017.
 */
public class TransactionDTO {

        @Column(name="ID")
        private Long id;

        @Column(name="CUSTOMER_ID")
        private Long customerId;

        @Column(name="MOVIE_ID")
        private Long movieId;

        @Column(name="N_DAYS")
        private Long nDays;

        @Column(name="N_EXTRA_DAYS")
        private Long nExtraDays;

        @Column(name="PRICE")
        private Long price;

        @Column(name="IS_SETTLED")
        private Long isSettled;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getCustomerId() {
                return customerId;
        }

        public void setCustomerId(Long customerId) {
                this.customerId = customerId;
        }

        public Long getMovieId() {
                return movieId;
        }

        public void setMovieId(Long movieId) {
                this.movieId = movieId;
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

        public Long getIsSettled() {
                return isSettled;
        }

        public void setIsSettled(Long isSettled) {
                this.isSettled = isSettled;
        }

        @Override
        public String toString() {
                return "TransactionDTO{" +
                        "id=" + id +
                        ", customerId=" + customerId +
                        ", movieId=" + movieId +
                        ", nDays=" + nDays +
                        ", nExtraDays=" + nExtraDays +
                        ", price=" + price +
                        ", isSettled=" + isSettled +
                        '}';
        }
}
