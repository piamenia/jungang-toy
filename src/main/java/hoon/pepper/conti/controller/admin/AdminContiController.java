package hoon.pepper.conti.controller.admin;

import hoon.pepper.common.wrapper.JpaPageContents;
import hoon.pepper.common.wrapper.PageContents;
import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.controller.model.*;
import hoon.pepper.conti.controller.model.request.ContiListRequest;
import hoon.pepper.conti.controller.model.request.ContiRequest;
import hoon.pepper.conti.converter.ContiConverter;
import hoon.pepper.conti.service.ContiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/conti")
@RequiredArgsConstructor
@Api(tags = "관리자 콘티 컨트롤러")
public class AdminContiController {
    private final ContiService contiService;
    private final ContiConverter contiConverter;

    @GetMapping("/list")
    @ApiOperation(value="콘티 리스트")
    public ResultResponse<PageContents<ContiListModel>> getContiList(@RequestParam Integer year,
                                                                     @RequestParam Integer month,
                                                                     @RequestParam(required = false, defaultValue = "1") int offset,
                                                                     @RequestParam(required = false, defaultValue = "99999") int limit) {
        Pageable pageable = PageRequest.of(offset - 1, limit);
        ContiListRequest contiListRequest = ContiListRequest.builder().year(year).month(month).build();
        Page<ContiListModel> results = contiService.getContiList(contiListRequest, pageable);
        JpaPageContents<ContiListModel, ContiListModel> pageContents =
            new JpaPageContents<ContiListModel, ContiListModel>(results) {
                @Override
                public ContiListModel converts(ContiListModel content) {
                    return contiConverter.converts(content);
                }
            };
        return new ResultResponse<>(pageContents);
    }

    @GetMapping("/list/half-year")
    @ApiOperation(value="콘티 리스트")
    public ResultResponse<PageContents<ContiListModel>> getContiListByHalfYear(@RequestParam Integer year,
                                                                               @RequestParam Integer halfYear,
                                                                               @RequestParam(required = false, defaultValue = "1") int offset,
                                                                               @RequestParam(required = false, defaultValue = "99999") int limit) {
        Pageable pageable = PageRequest.of(offset - 1, limit);
        ContiListRequest contiListRequest = ContiListRequest.builder().year(year).halfYear(halfYear).build();
        Page<ContiListModel> results = contiService.getContiList(contiListRequest, pageable);
        JpaPageContents<ContiListModel, ContiListModel> pageContents =
            new JpaPageContents<ContiListModel, ContiListModel>(results) {
                @Override
                public ContiListModel converts(ContiListModel content) {
                    return contiConverter.converts(content);
                }
            };
        return new ResultResponse<>(pageContents);
    }

    @GetMapping("/{contiId}")
    @ApiOperation(value="콘티 상세")
    public ResultResponse<ContiDetailModel> getContiDetail(@PathVariable Long contiId) {
        return new ResultResponse(contiService.getContiDetail(contiId));
    }

    @PostMapping
    @ApiOperation(value="콘티 생성")
    public ResultResponse<EmptyResultModel> postConti(@RequestBody ContiRequest conti) {
        contiService.postConti(conti);
        return new ResultResponse<>(new EmptyResultModel());
    }

    @PutMapping
    @ApiOperation(value="콘티 수정")
    public ResultResponse<EmptyResultModel> putConti(@RequestBody ContiRequest conti) {
        contiService.putConti(conti);
        return new ResultResponse<>(new EmptyResultModel());
    }

    @DeleteMapping("/{contiId}")
    @ApiOperation(value="콘티 삭제")
    public ResultResponse<EmptyResultModel> deleteConti(@PathVariable Long contiId) {
        contiService.deleteConti(contiId);
        return new ResultResponse<>(new EmptyResultModel());
    }

    @PostMapping("/password/{contiId}")
    @ApiOperation("콘티 암호 체크")
    public ResultResponse<BooleanResultModel> checkContiPassword(@PathVariable Long contiId, @RequestBody Map<String, Object> body) {
        return new ResultResponse<>(new BooleanResultModel(contiService.checkContiPassword(contiId, (String)body.get("password"))));
    }
}
