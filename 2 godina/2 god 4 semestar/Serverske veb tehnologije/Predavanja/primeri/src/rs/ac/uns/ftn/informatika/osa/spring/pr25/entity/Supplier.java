package rs.ac.uns.ftn.informatika.osa.spring.pr25.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="suppliers")
public class Supplier implements Serializable {

  @Id
  @GeneratedValue(strategy=IDENTITY)
  @Column(name="supplier_id", unique=true, nullable=false)
  private Integer id;
  
  @Column(name="sup_name", unique=false, nullable=false)
  private String name;
  
  @Column(name="sup_address", unique=false, nullable=false)
  private String address;
  
  @OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="supplier")
  private Set<Product> products = new HashSet<Product>();

  public void add(Product p) {
    if (p.getSupplier() != null)
      p.getSupplier().getProducts().remove(p);
    p.setSupplier(this);
    getProducts().add(p);
  }
  
  public Supplier() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }
  
  public String toString() {
    return "(Supplier)[id="+id+",name="+name+",address="+address+"]";
  }
}
