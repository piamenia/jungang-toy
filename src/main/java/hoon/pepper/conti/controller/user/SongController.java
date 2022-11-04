package hoon.pepper.conti.controller.user;

import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.controller.model.SongModel;
import hoon.pepper.conti.converter.SongConverter;
import hoon.pepper.conti.service.SongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/song")
@RequiredArgsConstructor
@Api(tags = "찬양 컨트롤러")
public class SongController {
    private final SongService songService;
    private final SongConverter songConverter;

    @GetMapping("/list/{contiId}")
    @ApiOperation(value="찬양 리스트")
    public ResultResponse<List<SongModel>> getSongList(@PathVariable Long contiId) {
        return new ResultResponse<>(songConverter.converts(songService.getSongList(contiId)));
    }
}
