package com.example.ams.database.rooms.dao;

/**
 * Project Name : AMS
 *
 * @author VE00YM572
 * @company YMSLI
 * @date 15-1-2024
 * Copyright (c) 2024, Yamaha Motor Solutions (INDIA) Pvt Ltd.
 * Revision History
 * ----------------------------------------------------------
 * Modified By          Modified On
 * VE00YM572            15-1-2024
 */

public class AssetAllocationInfo {
    private String name;
    private String ein;
    private boolean allocation_status;
    private String allocation_date;


    public AssetAllocationInfo(String name, String ein, boolean allocation_status,String allocation_date) {
        this.name = name;
        this.ein = ein;
        this.allocation_status = allocation_status;
        this.allocation_date = allocation_date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public boolean isAllocation_status() {
        return allocation_status;
    }

    public void setAllocation_status(boolean allocation_status) {
        this.allocation_status = allocation_status;
    }

    public String getAllocation_date() {
        return allocation_date;
    }

    public void setAllocation_date(String allocation_date) {
        this.allocation_date = allocation_date;
    }
}
