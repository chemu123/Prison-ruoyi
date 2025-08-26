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
import com.prison.prisoner.domain.Inmates;
import com.prison.prisoner.service.IInmatesService;
import com.prison.common.utils.poi.ExcelUtil;
import com.prison.common.core.page.TableDataInfo;

/**
 * 囚犯信息Controller
 * 
 * @author che
 * @date 2024-07-01
 */
@RestController
@RequestMapping("/prisoner/inmates")
public class InmatesController extends BaseController
{
    @Autowired
    private IInmatesService inmatesService;

    /**
     * 查询囚犯信息列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:list')")
    @GetMapping("/list")
    public TableDataInfo list(Inmates inmates)
    {
        startPage();
        List<Inmates> list = inmatesService.selectInmatesList(inmates);
        return getDataTable(list);
    }

    /**
     * 导出囚犯信息列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:export')")
    @Log(title = "囚犯信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inmates inmates)
    {
        List<Inmates> list = inmatesService.selectInmatesList(inmates);
        ExcelUtil<Inmates> util = new ExcelUtil<Inmates>(Inmates.class);
        util.exportExcel(response, list, "囚犯信息数据");
    }

    /**
     * 获取囚犯信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:query')")
    @GetMapping(value = "/{inmateId}")
    public AjaxResult getInfo(@PathVariable("inmateId") Long inmateId)
    {
        return success(inmatesService.selectInmatesByInmateId(inmateId));
    }

    /**
     * 新增囚犯信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:add')")
    @Log(title = "囚犯信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inmates inmates)
    {
        return toAjax(inmatesService.insertInmates(inmates));
    }

    /**
     * 修改囚犯信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:edit')")
    @Log(title = "囚犯信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inmates inmates)
    {
        return toAjax(inmatesService.updateInmates(inmates));
    }

    /**
     * 删除囚犯信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:inmates:remove')")
    @Log(title = "囚犯信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inmateIds}")
    public AjaxResult remove(@PathVariable Long[] inmateIds)
    {
        return toAjax(inmatesService.deleteInmatesByInmateIds(inmateIds));
    }
}
