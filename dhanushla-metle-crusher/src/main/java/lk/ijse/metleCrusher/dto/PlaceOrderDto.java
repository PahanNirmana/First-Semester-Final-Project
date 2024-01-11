package lk.ijse.metleCrusher.dto;

import lk.ijse.metleCrusher.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class PlaceOrderDto {
        private String orderId;
        private LocalDate date;
        private String customerId;
        private List<CartTm> cartTmList = new ArrayList<>();
    }

