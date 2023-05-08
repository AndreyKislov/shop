package ua.kislov.shop_back.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityShopClient {
    private long clientId;

    private String username;


    private String password;

    private String role;

    @Override
    public String toString() {
        return "SecurityShopClient{" +
                "clientId=" + clientId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
