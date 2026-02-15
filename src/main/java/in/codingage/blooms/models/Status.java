package in.codingage.blooms.models;



public enum Status {
    PUBLISHED("Published"),
    INREVIEW("InReview"),
    REJECTED("Rejected"),
    UPDATED("Updated"),;

    private final String displayName;

    private Status(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}