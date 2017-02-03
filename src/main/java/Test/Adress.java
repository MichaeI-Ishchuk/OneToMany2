package Test;

import javax.persistence.*;

/**
 * Created by nata on 26.01.2017.
 */

@Entity
@Table(name="adress")
public class Adress {

        @Id
        @GeneratedValue
        int id;


    @Column (name="client_adress")
    String clientAdress;

    ///  єто для бістрого поиска добовляем клиента реально колонки не будет
    @OneToOne (mappedBy = "adress", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    Client client=new Client();

    public Adress() {
    }

    public int getId() {
        return id;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
