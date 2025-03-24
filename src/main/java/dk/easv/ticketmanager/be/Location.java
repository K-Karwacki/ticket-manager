package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

@Entity
@Table(name = "Location")
public class Location
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ID;
  @Column(name = "name") private String name;
  @Column(name = "address1") private String address1;
  @Column(name = "address2") private String address2;
  @Column(name = "address3") private String address3;
  @Column(name = "city") private String city;
  @Column(name = "post_code") private String postCode;


  public Location(){}

  public Location(String name, String address1, String address2, String address3, String city, String postCode){
    this.name = name;
    this.address1 = address1;
    this.address2 = address2;
    this.address3 = address3;
    this.city = city;
    this.postCode = postCode;
  }

  @Override
  public String toString() {
    return name + ", " + address1 + ", " + address2 + ", " + address3 + ", " + city + ", " + postCode;
  }
  public void setAddress1(String address1){
    this.address1 = address1;
  }
  public void setAddress2(String address2){
    this.address2 = address2;
  }
  public void setAddress3(String address3){
    this.address3 = address3;
  }
  public void setCity(String city){
    this.city = city;
  }
  public void setPostCode(String postCode){
    this.postCode = postCode;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }
}
