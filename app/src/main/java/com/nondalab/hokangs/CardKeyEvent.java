package com.nondalab.hokangs;

public class CardKeyEvent  {
    interface CardKeyEventCallback {
        void cardKeyUsed();
    }

    private boolean canOpen = false;
    private CardKeyEventCallback callback = null;

    static CardKeyEvent sharedObject;

    private CardKeyEvent() {

    }

    public static CardKeyEvent getInstance() {
        if(sharedObject == null){
            sharedObject = new CardKeyEvent();
        }
        return sharedObject;
    }


    public boolean isCanOpen() {
        return canOpen;
    }

    public void setCanOpen(boolean canOpen) {
        this.canOpen = canOpen;
    }


    public void setCallback(CardKeyEventCallback callback) {
        this.callback = callback;
    }

    public void sendEvent() {
        if(callback != null) {
            callback.cardKeyUsed();;
        }
    }
}
