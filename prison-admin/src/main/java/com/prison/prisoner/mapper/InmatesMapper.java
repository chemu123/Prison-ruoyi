package com.prison.prisoner.mapper;

import java.util.List;
import com.prison.prisoner.domain.Inmates;
import com.prison.prisoner.domain.Cells;

/**
 * 囚犯信息Mapper接口
 * 
 * @author che
 * @date 2024-07-01
 */
public interface InmatesMapper 
{
    /**
     * 查询囚犯信息
     * 
     * @param inmateId 囚犯信息主键
     * @return 囚犯信息
     */
    public Inmates selectInmatesByInmateId(Long inmateId);

    /**
     * 查询囚犯信息列表
     * 
     * @param inmates 囚犯信息
     * @return 囚犯信息集合
     */
    public List<Inmates> selectInmatesList(Inmates inmates);

    /**
     * 新增囚犯信息
     * 
     * @param inmates 囚犯信息
     * @return 结果
     */
    public int insertInmates(Inmates inmates);

    /**
     * 修改囚犯信息
     * 
     * @param inmates 囚犯信息
     * @return 结果
     */
    public int updateInmates(Inmates inmates);

    /**
     * 删除囚犯信息
     * 
     * @param inmateId 囚犯信息主键
     * @return 结果
     */
    public int deleteInmatesByInmateId(Long inmateId);

    /**
     * 批量删除囚犯信息
     * 
     * @param inmateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInmatesByInmateIds(Long[] inmateIds);

    /**
     * 批量删除牢房信息
     * 
     * @param inmateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCellsByCellIds(Long[] inmateIds);
    
    /**
     * 批量新增牢房信息
     * 
     * @param cellsList 牢房信息列表
     * @return 结果
     */
    public int batchCells(List<Cells> cellsList);
    

    /**
     * 通过囚犯信息主键删除牢房信息信息
     * 
     * @param inmateId 囚犯信息ID
     * @return 结果
     */
    public int deleteCellsByCellId(Long inmateId);
}
