package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Category {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean active;
    private LocalDateTime createdDTTM;
    private String createdBy;
    private String status;

//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getCreatedDTTM() {
//        return createdDTTM;
//    }
//
//    public void setCreatedDTTM(LocalDateTime createdDTTM) {
//        this.createdDTTM = createdDTTM;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getImageUrl() {
//        return cUrl;
//    }
//
//    public void setImageUrl(String cUrl) {
//        this.cUrl = cUrl;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}
