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
    if(address2.isEmpty() && address3.isEmpty()){
      return name + ", " + address1 + ", " + city + ", " + postCode;
    }
    else if(address2.isEmpty()){
      return name + ", " + address1 + ", "  + address3 + ", " + city + ", " + postCode;
    }
    else if(address3.isEmpty()){
      return name + ", " + address1 + ", "  + address2 + ", " + city + ", " + postCode;
    }
    return name + ", " + address1 + ", " + address2 + ", " + address3 + ", " + city + ", " + postCode;
  }

}
