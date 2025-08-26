package com.prison.prisoner.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prison.prisoner.mapper.CellsMapper;
import com.prison.prisoner.domain.Cells;
import com.prison.prisoner.service.ICellsService;

/**
 * 牢房信息Service业务层处理
 * 
 * @author che
 * @date 2024-07-01
 */
@Service
public class CellsServiceImpl implements ICellsService 
{
    @Autowired
    private CellsMapper cellsMapper;

    /**
     * 查询牢房信息
     * 
     * @param cellId 牢房信息主键
     * @return 牢房信息
     */
    @Override
    public Cells selectCellsByCellId(Long cellId)
    {
        return cellsMapper.selectCellsByCellId(cellId);
    }

    /**
     * 查询牢房信息列表
     * 
     * @param cells 牢房信息
     * @return 牢房信息
     */
    @Override
    public List<Cells> selectCellsList(Cells cells)
    {
        return cellsMapper.selectCellsList(cells);
    }

    /**
     * 新增牢房信息
     * 
     * @param cells 牢房信息
     * @return 结果
     */
    @Override
    public int insertCells(Cells cells)
    {
        return cellsMapper.insertCells(cells);
    }

    /**
     * 修改牢房信息
     * 
     * @param cells 牢房信息
     * @return 结果
     */
    @Override
    public int updateCells(Cells cells)
    {
        return cellsMapper.updateCells(cells);
    }

    /**
     * 批量删除牢房信息
     * 
     * @param cellIds 需要删除的牢房信息主键
     * @return 结果
     */
    @Override
    public int deleteCellsByCellIds(Long[] cellIds)
    {
        return cellsMapper.deleteCellsByCellIds(cellIds);
    }

    /**
     * 删除牢房信息信息
     * 
     * @param cellId 牢房信息主键
     * @return 结果
     */
    @Override
    public int deleteCellsByCellId(Long cellId)
    {
        return cellsMapper.deleteCellsByCellId(cellId);
    }
}
