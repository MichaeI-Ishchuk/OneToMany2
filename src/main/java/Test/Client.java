package Test;

import javax.persistence.*;

/**
 * Created by nata on 26.01.2017.
 */


@Entity
@Table (name="clients")
public class Client {

    @Id
    @GeneratedValue
    int id;

    @Column (name="name")
        String name;


    @Column (name="surname")
    String surname;

@OneToOne (fetch=FetchType.LAZY)
        @JoinColumn(name="id_adress")
    Adress adress;
@ManyToOne
@JoinColumn(name ="manager_id")
Manager manager;



    public Client() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}


