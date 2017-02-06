package com.example.sizebook;

/**
 * This class contains the dimensions associated with each Person record
 * stored by the user
 * Created by Shivansh on 2017-01-31.
 */
public class Dimensions {

    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;

    /**
     * Instantiates a new Dimensions.
     *
     * @param neck   the neck
     * @param bust   the bust
     * @param chest  the chest
     * @param waist  the waist
     * @param hip    the hip
     * @param inseam the inseam
     */
    public Dimensions(String neck, String bust, String chest, String waist, String hip, String inseam) {
        this.inseam = inseam;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.bust = bust;
        this.neck = neck;
    }
    @Override
    public String toString() {
        return "DimensionsObject [neck="+ neck +", bust=" + bust + ", chest=" + chest + ", waist="+ waist
                + ", hip=" + hip + ", inseam=" + inseam +"]";
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public String getBust() {
        return bust;
    }

    /**
     * Sets bust.
     *
     * @param bust the bust
     */
    public void setBust(String bust) {
        this.bust = bust;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public String getChest() {
        return chest;
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(String chest) {
        this.chest = chest;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public String getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(String waist) {
        this.waist = waist;
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public String getHip() {
        return hip;
    }

    /**
     * Sets hip.
     *
     * @param hip the hip
     */
    public void setHip(String hip) {
        this.hip = hip;
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public String getInseam() {
        return inseam;
    }

    /**
     * Sets inseam.
     *
     * @param inseam the inseam
     */
    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public String getNeck() {
        return neck;
    }

    /**
     * Sets neck.
     *
     * @param neck the neck
     */
    public void setNeck(String neck) {
        this.neck = neck;
    }


}
