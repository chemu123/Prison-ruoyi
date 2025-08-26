package com.prison.prisoner.service;

import java.util.List;
import com.prison.prisoner.domain.Inmates;

/**
 * 囚犯信息Service接口
 * 
 * @author che
 * @date 2024-07-01
 */
public interface IInmatesService 
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
     * 批量删除囚犯信息
     * 
     * @param inmateIds 需要删除的囚犯信息主键集合
     * @return 结果
     */
    public int deleteInmatesByInmateIds(Long[] inmateIds);

    /**
     * 删除囚犯信息信息
     * 
     * @param inmateId 囚犯信息主键
     * @return 结果
     */
    public int deleteInmatesByInmateId(Long inmateId);
}
