package ua.kislov.shop_back.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopClientDTO {
    private long id;
    private String clientName;
    private String surname;
    private String email;
    private String number;

}
