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
  @Column(name = "address") private String address;
  @Column(name = "city") private String city;
  @Column(name = "post_code") private String postCode;


  public Location(){}

  public Location(String name, String address, String city, String postCode){
    this.name = name;
    this.address = address;
    this.city = city;
    this.postCode = postCode;
  }

  @Override
  public String toString() {
      return name + ", " + address + ", " + city + ", " + postCode;
  }
  public void setAddress(String address){
    this.address = address;
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
  public String getPostCode() { return postCode; }
  public String getAddress() { return address; }
  public String getLocationName() { return name; }
}
