package test.app.nobroker.nobrokertest.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sashwat on 8/10/2015.
 */
public class FeedIPropertyItem {
    private String propertyName;
    private String propertyId;
    private String propertyAddress;
    private String propertyImage;
    private String propertyFurnishing;
    private int propertyRent;
    private int propertySize;
    private int numberOfBathrooms;

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    public FeedIPropertyItem() {
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }


    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    public int getPropertyRent() {
        return propertyRent;
    }

    public void setPropertyRent(int propertyRent) {
        this.propertyRent = propertyRent;
    }

    public int getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(int propertySize) {
        this.propertySize = propertySize;
    }

    public String getPropertyFurnishing() {
        return propertyFurnishing;
    }

    public void setPropertyFurnishing(String propertyFurnishing) {
        this.propertyFurnishing = propertyFurnishing;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }


}
