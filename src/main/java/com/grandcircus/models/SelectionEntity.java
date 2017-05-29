package com.grandcircus.models;

import javax.persistence.*;

/**
 * Created by aborn601722 on 5/26/17.
 */
@Entity
@Table(name = "Selection", schema = "RideShare", catalog = "")
public class SelectionEntity {
    private int selectionId;
    private String fromAddress;
    private String toAddress;
    private String selection;
    private String price;
    private int timeDistance;

    @Id
    @Column(name = "SelectionID", nullable = false)
    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    @Basic
    @Column(name = "FromAddress", nullable = false, length = -1)
    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Basic
    @Column(name = "ToAddress", nullable = false, length = -1)
    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    @Basic
    @Column(name = "Selection", nullable = false, length = -1)
    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Basic
    @Column(name = "Price", nullable = false, length = -1)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Basic
    @Column(name = "TimeDistance", nullable = false)
    public int getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(int timeDistance) {
        this.timeDistance = timeDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectionEntity that = (SelectionEntity) o;

        if (selectionId != that.selectionId) return false;
        if (timeDistance != that.timeDistance) return false;
        if (fromAddress != null ? !fromAddress.equals(that.fromAddress) : that.fromAddress != null) return false;
        if (toAddress != null ? !toAddress.equals(that.toAddress) : that.toAddress != null) return false;
        if (selection != null ? !selection.equals(that.selection) : that.selection != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = selectionId;
        result = 31 * result + (fromAddress != null ? fromAddress.hashCode() : 0);
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + (selection != null ? selection.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + timeDistance;
        return result;
    }
}
