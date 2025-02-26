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



}
