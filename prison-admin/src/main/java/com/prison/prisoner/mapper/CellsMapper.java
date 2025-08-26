package com.prison.prisoner.mapper;

import java.util.List;
import com.prison.prisoner.domain.Cells;

/**
 * 牢房信息Mapper接口
 * 
 * @author che
 * @date 2024-07-01
 */
public interface CellsMapper 
{
    /**
     * 查询牢房信息
     * 
     * @param cellId 牢房信息主键
     * @return 牢房信息
     */
    public Cells selectCellsByCellId(Long cellId);

    /**
     * 查询牢房信息列表
     * 
     * @param cells 牢房信息
     * @return 牢房信息集合
     */
    public List<Cells> selectCellsList(Cells cells);

    /**
     * 新增牢房信息
     * 
     * @param cells 牢房信息
     * @return 结果
     */
    public int insertCells(Cells cells);

    /**
     * 修改牢房信息
     * 
     * @param cells 牢房信息
     * @return 结果
     */
    public int updateCells(Cells cells);

    /**
     * 删除牢房信息
     * 
     * @param cellId 牢房信息主键
     * @return 结果
     */
    public int deleteCellsByCellId(Long cellId);

    /**
     * 批量删除牢房信息
     * 
     * @param cellIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCellsByCellIds(Long[] cellIds);
}
