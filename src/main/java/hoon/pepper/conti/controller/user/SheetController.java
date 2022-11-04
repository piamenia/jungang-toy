package hoon.pepper.conti.controller.user;

import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.controller.model.SheetModel;
import hoon.pepper.conti.converter.SheetConverter;
import hoon.pepper.conti.service.SheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/sheet")
@RequiredArgsConstructor
@Api(tags = "악보 컨트롤러")
public class SheetController {
    private final SheetService sheetService;
    private final SheetConverter sheetConverter;

    @GetMapping("/list/{songId}")
    @ApiOperation(value="악보 리스트")
    public ResultResponse<List<SheetModel>> getSheetList(@PathVariable Long songId) {
        return new ResultResponse<>(sheetConverter.converts(sheetService.getSheetList(songId)));
    }
}
