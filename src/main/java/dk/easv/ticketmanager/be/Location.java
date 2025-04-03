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

  public long getID()
  {
    return ID;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getPostCode()
  {
    return postCode;
  }

  public void setPostCode(String postCode)
  {
    this.postCode = postCode;
  }

  public String getLocationName() {
    return name;
  }

  @Override
  public String toString(){
    return name + ", " + address + ", " + postCode + ", " + city;
  }
}
