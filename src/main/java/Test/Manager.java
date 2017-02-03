package Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nata on 26.01.2017.
 */
@Entity
@Table(name="manager")

public class Manager {




    @Id
    @GeneratedValue
    int id;

    @Column(name="name")
    String name;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY )
    List<Client> clientList = new ArrayList<Client>();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public Manager() {
    }
}
