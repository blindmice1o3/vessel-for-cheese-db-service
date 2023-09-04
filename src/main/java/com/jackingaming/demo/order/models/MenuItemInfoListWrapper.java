package com.jackingaming.demo.order.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class MenuItemInfoListWrapper {
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdOn;
    private List<MenuItemInfo> menuItemInfos;

    public MenuItemInfoListWrapper() {
    }

    public MenuItemInfoListWrapper(LocalDateTime createdOn, List<MenuItemInfo> menuItemInfos) {
        this.createdOn = createdOn;
        this.menuItemInfos = menuItemInfos;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public List<MenuItemInfo> getMenuItemInfos() {
        return menuItemInfos;
    }

    public void setMenuItemInfos(List<MenuItemInfo> menuItemInfos) {
        this.menuItemInfos = menuItemInfos;
    }
}
