package com.prison.prisoner.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.prison.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.prison.prisoner.domain.Cells;
import com.prison.prisoner.mapper.InmatesMapper;
import com.prison.prisoner.domain.Inmates;
import com.prison.prisoner.service.IInmatesService;

/**
 * 囚犯信息Service业务层处理
 * 
 * @author che
 * @date 2024-07-01
 */
@Service
public class InmatesServiceImpl implements IInmatesService 
{
    @Autowired
    private InmatesMapper inmatesMapper;

    /**
     * 查询囚犯信息
     * 
     * @param inmateId 囚犯信息主键
     * @return 囚犯信息
     */
    @Override
    public Inmates selectInmatesByInmateId(Long inmateId)
    {
        return inmatesMapper.selectInmatesByInmateId(inmateId);
    }

    /**
     * 查询囚犯信息列表
     * 
     * @param inmates 囚犯信息
     * @return 囚犯信息
     */
    @Override
    public List<Inmates> selectInmatesList(Inmates inmates)
    {
        return inmatesMapper.selectInmatesList(inmates);
    }

    /**
     * 新增囚犯信息
     * 
     * @param inmates 囚犯信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertInmates(Inmates inmates)
    {
        int rows = inmatesMapper.insertInmates(inmates);
        insertCells(inmates);
        return rows;
    }

    /**
     * 修改囚犯信息
     * 
     * @param inmates 囚犯信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateInmates(Inmates inmates)
    {
        inmatesMapper.deleteCellsByCellId(inmates.getInmateId());
        insertCells(inmates);
        return inmatesMapper.updateInmates(inmates);
    }

    /**
     * 批量删除囚犯信息
     * 
     * @param inmateIds 需要删除的囚犯信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInmatesByInmateIds(Long[] inmateIds)
    {
        inmatesMapper.deleteCellsByCellIds(inmateIds);
        return inmatesMapper.deleteInmatesByInmateIds(inmateIds);
    }

    /**
     * 删除囚犯信息信息
     * 
     * @param inmateId 囚犯信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInmatesByInmateId(Long inmateId)
    {
        inmatesMapper.deleteCellsByCellId(inmateId);
        return inmatesMapper.deleteInmatesByInmateId(inmateId);
    }

    /**
     * 新增牢房信息信息
     * 
     * @param inmates 囚犯信息对象
     */
    public void insertCells(Inmates inmates)
    {
        List<Cells> cellsList = inmates.getCellsList();
        Long inmateId = inmates.getInmateId();
        if (StringUtils.isNotNull(cellsList))
        {
            List<Cells> list = new ArrayList<Cells>();
            for (Cells cells : cellsList)
            {
                cells.setCellId(inmateId);
                list.add(cells);
            }
            if (list.size() > 0)
            {
                inmatesMapper.batchCells(list);
            }
        }
    }
}
