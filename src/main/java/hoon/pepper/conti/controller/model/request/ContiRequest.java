package hoon.pepper.conti.controller.model.request;

import hoon.pepper.common.code.Depart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContiRequest {
    private Long contiId;
    private Long categoryId;
    private Depart depart;
    private LocalDate date;
    private String title;
    private String password;
    private List<SongRequest> songList;
}
