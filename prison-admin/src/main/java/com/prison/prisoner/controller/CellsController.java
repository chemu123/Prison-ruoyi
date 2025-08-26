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
import com.prison.prisoner.domain.Cells;
import com.prison.prisoner.service.ICellsService;
import com.prison.common.utils.poi.ExcelUtil;
import com.prison.common.core.page.TableDataInfo;

/**
 * 牢房信息Controller
 * 
 * @author che
 * @date 2024-07-01
 */
@RestController
@RequestMapping("/prisoner/cells")
public class CellsController extends BaseController
{
    @Autowired
    private ICellsService cellsService;

    /**
     * 查询牢房信息列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cells cells)
    {
        startPage();
        List<Cells> list = cellsService.selectCellsList(cells);
        return getDataTable(list);
    }

    /**
     * 导出牢房信息列表
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:export')")
    @Log(title = "牢房信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cells cells)
    {
        List<Cells> list = cellsService.selectCellsList(cells);
        ExcelUtil<Cells> util = new ExcelUtil<Cells>(Cells.class);
        util.exportExcel(response, list, "牢房信息数据");
    }

    /**
     * 获取牢房信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:query')")
    @GetMapping(value = "/{cellId}")
    public AjaxResult getInfo(@PathVariable("cellId") Long cellId)
    {
        return success(cellsService.selectCellsByCellId(cellId));
    }

    /**
     * 新增牢房信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:add')")
    @Log(title = "牢房信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cells cells)
    {
        return toAjax(cellsService.insertCells(cells));
    }

    /**
     * 修改牢房信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:edit')")
    @Log(title = "牢房信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cells cells)
    {
        return toAjax(cellsService.updateCells(cells));
    }

    /**
     * 删除牢房信息
     */
    @PreAuthorize("@ss.hasPermi('prisoner:cells:remove')")
    @Log(title = "牢房信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cellIds}")
    public AjaxResult remove(@PathVariable Long[] cellIds)
    {
        return toAjax(cellsService.deleteCellsByCellIds(cellIds));
    }
}
