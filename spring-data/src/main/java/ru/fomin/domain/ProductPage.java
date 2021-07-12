package ru.fomin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPage {
    List<Product> productList;
    int pageCount;
}
