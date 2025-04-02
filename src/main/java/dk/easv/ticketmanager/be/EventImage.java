package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

@Entity
@Table(name = "EventImage")
public class EventImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Lob // Use @Lob for large binary data
    @Column(name = "image_data", columnDefinition = "VARBINARY(MAX)")
    private byte[] imageData;

    public EventImage(){}

    public EventImage(byte[] imageData){
        this.imageData = imageData;
    }

    public void setId(Long ID) {
        this.ID = ID;
    }

    public Long getId() {
        return ID;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public byte[] getImageData() {
        return imageData;
    }
}
