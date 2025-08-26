package com.prison.prisoner.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prison.common.annotation.Log;
import com.prison.common.core.controller.BaseController;
import com.prison.common.core.domain.AjaxResult;
import com.prison.common.enums.BusinessType;
import com.prison.prisoner.domain.PrisonerPhotos;
import com.prison.prisoner.service.IPrisonerPhotosService;
import com.prison.common.utils.poi.ExcelUtil;
import com.prison.common.core.page.TableDataInfo;

/**
 * 囚犯照片Controller
 * 
 * @author che
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/prisoner/photos")
public class PrisonerPhotosController extends BaseController
{
    @Autowired
    private IPrisonerPhotosService prisonerPhotosService;

    /**
     * 查询囚犯照片列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:list')")
    @GetMapping("/list")
    public TableDataInfo list(PrisonerPhotos prisonerPhotos)
    {
        startPage();
        List<PrisonerPhotos> list = prisonerPhotosService.selectPrisonerPhotosList(prisonerPhotos);
        return getDataTable(list);
    }

    /**
     * 导出囚犯照片列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:export')")
    @Log(title = "囚犯照片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PrisonerPhotos prisonerPhotos)
    {
        List<PrisonerPhotos> list = prisonerPhotosService.selectPrisonerPhotosList(prisonerPhotos);
        ExcelUtil<PrisonerPhotos> util = new ExcelUtil<PrisonerPhotos>(PrisonerPhotos.class);
        util.exportExcel(response, list, "囚犯照片数据");
    }

    /**
     * 获取囚犯照片详细信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:query')")
    @GetMapping(value = "/{firstName}")
    public AjaxResult getInfo(@PathVariable("firstName") String firstName)
    {
        return success(prisonerPhotosService.selectPrisonerPhotosByFirstName(firstName));
    }

    /**
     * 新增囚犯照片
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:add')")
    @Log(title = "囚犯照片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PrisonerPhotos prisonerPhotos)
    {
        return toAjax(prisonerPhotosService.insertPrisonerPhotos(prisonerPhotos));
    }

    /**
     * 修改囚犯照片
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:edit')")
    @Log(title = "囚犯照片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PrisonerPhotos prisonerPhotos)
    {
        return toAjax(prisonerPhotosService.updatePrisonerPhotos(prisonerPhotos));
    }

    /**
     * 删除囚犯照片
     */
    @PreAuthorize("@ss.hasPermi('prisoner:photos:remove')")
    @Log(title = "囚犯照片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{firstNames}")
    public AjaxResult remove(@PathVariable String[] firstNames)
    {
        return toAjax(prisonerPhotosService.deletePrisonerPhotosByFirstNames(firstNames));
    }
}
