package com.prison.prisoner.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prison.prisoner.mapper.PrisonerPhotosMapper;
import com.prison.prisoner.domain.PrisonerPhotos;
import com.prison.prisoner.service.IPrisonerPhotosService;

/**
 * 囚犯照片Service业务层处理
 * 
 * @author che
 * @date 2024-07-02
 */
@Service
public class PrisonerPhotosServiceImpl implements IPrisonerPhotosService 
{
    @Autowired
    private PrisonerPhotosMapper prisonerPhotosMapper;

    /**
     * 查询囚犯照片
     * 
     * @param firstName 囚犯照片主键
     * @return 囚犯照片
     */
    @Override
    public PrisonerPhotos selectPrisonerPhotosByFirstName(String firstName)
    {
        return prisonerPhotosMapper.selectPrisonerPhotosByFirstName(firstName);
    }

    /**
     * 查询囚犯照片列表
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 囚犯照片
     */
    @Override
    public List<PrisonerPhotos> selectPrisonerPhotosList(PrisonerPhotos prisonerPhotos)
    {
        return prisonerPhotosMapper.selectPrisonerPhotosList(prisonerPhotos);
    }

    /**
     * 新增囚犯照片
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 结果
     */
    @Override
    public int insertPrisonerPhotos(PrisonerPhotos prisonerPhotos)
    {
        return prisonerPhotosMapper.insertPrisonerPhotos(prisonerPhotos);
    }

    /**
     * 修改囚犯照片
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 结果
     */
    @Override
    public int updatePrisonerPhotos(PrisonerPhotos prisonerPhotos)
    {
        return prisonerPhotosMapper.updatePrisonerPhotos(prisonerPhotos);
    }

    /**
     * 批量删除囚犯照片
     * 
     * @param firstNames 需要删除的囚犯照片主键
     * @return 结果
     */
    @Override
    public int deletePrisonerPhotosByFirstNames(String[] firstNames)
    {
        return prisonerPhotosMapper.deletePrisonerPhotosByFirstNames(firstNames);
    }

    /**
     * 删除囚犯照片信息
     * 
     * @param firstName 囚犯照片主键
     * @return 结果
     */
    @Override
    public int deletePrisonerPhotosByFirstName(String firstName)
    {
        return prisonerPhotosMapper.deletePrisonerPhotosByFirstName(firstName);
    }
}
