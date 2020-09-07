package com.retailbank.jmscreditcardervice.Controller;

public class ApplyForCreditCardRequest {

    private int citizenNumber;
    private CardType cardType;

    public ApplyForCreditCardRequest(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public int getCitizenNumber() {
        return citizenNumber;
    }

    public void setCitizenNumber(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public enum CardType {
        GOLD
    }
}
