package com.nexters.gaetteok.user.constant;

import lombok.Getter;

@Getter
public enum MainScreenImage {

    BEFORE_WALKING("https://kr.object.ncloudstorage.com/gaetteok-image/home/home_beforewalking.png"),
    AFTER_WALKING("https://kr.object.ncloudstorage.com/gaetteok-image/home/home_afterwalking.png"),
    ;

    private final String imageUrl;

    MainScreenImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static String getImageUrl(boolean walkDone) {
        return walkDone ? AFTER_WALKING.imageUrl : BEFORE_WALKING.imageUrl;
    }

}
