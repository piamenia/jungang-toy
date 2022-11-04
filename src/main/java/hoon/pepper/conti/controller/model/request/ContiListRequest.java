package hoon.pepper.conti.controller.model.request;

import hoon.pepper.common.code.Depart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContiListRequest {
    private Long categoryId;
    private Depart depart;
    private int year;
    private int month;
    private int offset = 1;
    private int limit = 10;
}
