package com.example.bds.api;

import com.example.bds.constant.SystemConstant;
import com.example.bds.dto.BuildingDTO;
import com.example.bds.dto.BuildingSearchDTO;
import com.example.bds.dto.PagedResponseDTO;
import com.example.bds.dto.ResponseDTO;
import com.example.bds.exception.NotFoundException;
import com.example.bds.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/{buildingId}")
    public ResponseDTO<BuildingDTO> getDetailBuilding(@PathVariable(name = "buildingId") Long buildingId){
        ResponseDTO<BuildingDTO> result = new ResponseDTO<>();
        BuildingDTO buildingDTO = buildingService.findBuildingById(buildingId);
        if(buildingDTO != null){
            result.setMessage("Lấy tòa nhà thành công");
            result.setData(buildingDTO);
            return result;
        }
        throw new NotFoundException(SystemConstant.BUILDING_NOT_FOUND);
    }
    @GetMapping
    public ResponseDTO<PagedResponseDTO<BuildingDTO>> getBuildings(@ModelAttribute BuildingSearchDTO buildingSearch,
                                                                   @RequestParam(value="page",required = false) Integer page,
                                                                   @RequestParam(value="pageSize",required = false) Integer pageSize){
        ResponseDTO<PagedResponseDTO<BuildingDTO>> result = new ResponseDTO<>();
        PagedResponseDTO<BuildingDTO> pagedResponse = new PagedResponseDTO<>();
        if(pageSize == null){
            pageSize = 2;
        }
        if(page == null || page < 1){
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<BuildingDTO> objects = buildingService.findBuilding(buildingSearch,pageable);
        pagedResponse.setPageSize(pageable.getPageSize());
        pagedResponse.setCurrentPage(objects.getNumber()+1);
        pagedResponse.setTotalPages(objects.getTotalPages());
        pagedResponse.setTotalItems(objects.getTotalElements());
        pagedResponse.setListResult(objects.getContent());
        result.setMessage("Lấy danh sách tòa nhà thành công");
        result.setData(pagedResponse);
        return result;
    }
    @PostMapping
    public ResponseDTO<BuildingDTO> createBuilding(@RequestBody @Valid BuildingDTO newBuilding){
        ResponseDTO<BuildingDTO> result = new ResponseDTO<>();
        BuildingDTO buildingDTO = buildingService.save(newBuilding);
        result.setMessage("Thêm mới thành công");
        result.setData(buildingDTO);
        return result;
    }

    @PutMapping
    public ResponseDTO<BuildingDTO> updateBuilding(@RequestBody @Valid BuildingDTO newBuilding){
        ResponseDTO<BuildingDTO> result = new ResponseDTO<>();
        BuildingDTO buildingDTO = buildingService.save(newBuilding);
        result.setMessage("Cập nhật thành công");
        result.setData(buildingDTO);
        return result;
    }

    @DeleteMapping
    public ResponseDTO<Integer> deleteBuildings(@RequestBody List<Long> ids){
        ResponseDTO<Integer> result = new ResponseDTO<>();
        Integer countDeleted = buildingService.delete(ids);
        result.setMessage("Xóa thất bại");
        if(countDeleted != null && countDeleted > 0){
            result.setMessage("Xóa thành công");
        }
        result.setData(countDeleted);
        return result;
    }

}
