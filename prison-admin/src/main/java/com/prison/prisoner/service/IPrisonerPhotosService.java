package com.prison.prisoner.service;

import java.util.List;
import com.prison.prisoner.domain.PrisonerPhotos;

/**
 * 囚犯照片Service接口
 * 
 * @author che
 * @date 2024-07-02
 */
public interface IPrisonerPhotosService 
{
    /**
     * 查询囚犯照片
     * 
     * @param firstName 囚犯照片主键
     * @return 囚犯照片
     */
    public PrisonerPhotos selectPrisonerPhotosByFirstName(String firstName);

    /**
     * 查询囚犯照片列表
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 囚犯照片集合
     */
    public List<PrisonerPhotos> selectPrisonerPhotosList(PrisonerPhotos prisonerPhotos);

    /**
     * 新增囚犯照片
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 结果
     */
    public int insertPrisonerPhotos(PrisonerPhotos prisonerPhotos);

    /**
     * 修改囚犯照片
     * 
     * @param prisonerPhotos 囚犯照片
     * @return 结果
     */
    public int updatePrisonerPhotos(PrisonerPhotos prisonerPhotos);

    /**
     * 批量删除囚犯照片
     * 
     * @param firstNames 需要删除的囚犯照片主键集合
     * @return 结果
     */
    public int deletePrisonerPhotosByFirstNames(String[] firstNames);

    /**
     * 删除囚犯照片信息
     * 
     * @param firstName 囚犯照片主键
     * @return 结果
     */
    public int deletePrisonerPhotosByFirstName(String firstName);
}
