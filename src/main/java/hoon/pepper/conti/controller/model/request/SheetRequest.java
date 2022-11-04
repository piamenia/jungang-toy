package hoon.pepper.conti.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetRequest {
    private Long songId;
    private Integer sheetOrder;
    private Long fileId;
}
