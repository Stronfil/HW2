package ru.fomin.free_progect.domains;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPage {
    List<Product> productList;
    int pageCount;
}
