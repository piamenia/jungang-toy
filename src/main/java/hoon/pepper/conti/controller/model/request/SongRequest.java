package hoon.pepper.conti.controller.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    private Long contiId;
    private Long songId;
    private String title;
    private Integer songOrder;
    private String link;
    private List<SheetRequest> sheetList;
}
