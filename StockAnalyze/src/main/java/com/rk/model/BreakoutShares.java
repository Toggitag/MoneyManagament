package com.rk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "PositiveBreakoutShares")
public class BreakoutShares {
    @Id
    String id;
    String sma30;
    String sma50;
    String sma150;
    String sma200;
    String rsi;
    String price;
    String upPercent;
}
