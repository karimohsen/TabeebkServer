/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.utilty;

/**
 *
 * @author HMA
 */
public class GenericMSP {
    private int msptypeId;
    private String msptypename;
    private int mspId;
    private String mspname;
    private String mspnamear;
    private String mspEmail;
    private int deleted;
    
    public int getMsptypeId() {
        return msptypeId;
    }

    public void setMsptypeId(int msptypeId) {
        this.msptypeId = msptypeId;
    }

    public String getMsptypename() {
        return msptypename;
    }

    public void setMsptypename(String msptypename) {
        this.msptypename = msptypename;
    }

    public int getMspId() {
        return mspId;
    }

    public void setMspId(int mspId) {
        this.mspId = mspId;
    }

    public String getMspname() {
        return mspname;
    }

    public void setMspname(String mspname) {
        this.mspname = mspname;
    }

    public String getMspnamear() {
        return mspnamear;
    }

    public void setMspnamear(String mspnamear) {
        this.mspnamear = mspnamear;
    }

    public String getMspEmail() {
        return mspEmail;
    }

    public void setMspEmail(String mspEmail) {
        this.mspEmail = mspEmail;
    }
    
    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    @Override
    public String toString(){
        return  msptypeId+" "+msptypename+" "+mspId+" "+mspname;
    }
}
