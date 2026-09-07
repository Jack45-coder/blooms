package in.codingage.blooms.models;


public enum Role {
    ROLE_USER, ROLE_ADMIN;


    public <R> R getName() {
        return (R) this.name();
    }
}